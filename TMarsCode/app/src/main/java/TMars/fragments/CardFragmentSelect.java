package TMars.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import TMars.model.Card;
import TMars.model.GlobalAssets;
import TMars.model.Player;
import TMars.model.TableauRow;
import TMars.model.TagRow;
import TMars.presenters.TableauPresenter;
import TMars.providers.TagProvider;
import edu.byu.cs.tweeter.R;

/**
 * The fragment that displays on the 'Following' tab.
 */
public class CardFragmentSelect extends Fragment {

    private static final String LOG_TAG = "TagFragment";
    private static final String PLAYER_KEY = "PlayerKey";

    private static final int LOADING_DATA_VIEW = 0;
    private static final int ITEM_VIEW = 1;
    private final Player player;

    private CardRecyclerViewAdapter cardRecyclerViewAdapter;

    private CardFragmentSelect(Player player) { this.player = player; }

    /**
     * Creates an instance of the fragment and places the user and auth token in an arguments
     * bundle assigned to the fragment.
     *
     * @param player the current player.
     * @return the fragment.
     */
    public static CardFragmentSelect newInstance(Player player) {
        CardFragmentSelect fragment = new CardFragmentSelect(player);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_findcard, container, false);

        RecyclerView cardRecyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        cardRecyclerView.setLayoutManager(layoutManager);

        cardRecyclerViewAdapter = new CardRecyclerViewAdapter();
        cardRecyclerView.setAdapter(cardRecyclerViewAdapter);

        cardRecyclerView.addOnScrollListener(new CardRecyclerViewPaginationScrollListener(layoutManager));

        return view;
    }

    public void refresh()
    {
        getActivity().getSupportFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }


    /**
     * The ViewHolder for the RecyclerView that displays the Following data.
     */
    private class CardHolder extends RecyclerView.ViewHolder {

        private final ImageView tag1;
        private final ImageView tag2;
        private final ImageView tag3;
        private final ImageView tag4;
        private final List<ImageView> tagIcons;

        private final TextView cid;
        private final TextView cost;
        private final TextView name;
        private final TextView text;
        private final TextView score;

        private final Button select;

        /**
         * Creates an instance and sets an OnClickListener for the user's row.
         *
         * @param itemView the view on which the user will be displayed.
         */
        CardHolder(@NonNull View itemView, int viewType) {
            super(itemView);

            if(viewType == ITEM_VIEW) {
                tag1 = itemView.findViewById(R.id.tag1);
                tag2 = itemView.findViewById(R.id.tag2);
                tag3 = itemView.findViewById(R.id.tag3);
                tag4 = itemView.findViewById(R.id.tag4);

                cid = itemView.findViewById(R.id.cardID);
                cost = itemView.findViewById(R.id.cardCost);
                name = itemView.findViewById(R.id.cardName);
                text = itemView.findViewById(R.id.cardText);
                score = itemView.findViewById(R.id.cardPoints);

                select = itemView.findViewById(R.id.selButton);

            } else {
                tag1 = null;
                tag2 = null;
                tag3 = null;
                tag4 = null;

                cid = null;
                cost = null;
                name = null;
                text = null;
                score = null;

                select = null;
            }

            tagIcons = Arrays.asList(tag1,tag2,tag3,tag4);

        }

        /**
         * Binds the user's data to the view.
         *
         * @param card the card.
         */
        @RequiresApi(api = Build.VERSION_CODES.M)
        void bindRow(Card card)
        {
            HashMap<TagProvider.CardTag, Integer> tags = card.getTags();
            int index = 0;
            for (TagProvider.CardTag tag : TagProvider.CardTag.values()) {
                if (tags.get(tag) != null)
                {
                    int count = tags.get(tag);
                    while (count > 0)
                    {
                        tagIcons.get(index).setImageResource(TagProvider.TagToImage(tag));
                        ++index;
                        --count;
                    }
                }
            }
            for (; index < 4; ++index) tagIcons.get(index).setVisibility(View.INVISIBLE);

            cost.setText(String.valueOf(card.getCost()));
            cid.setText(card.getId());
            name.setText(card.getName());

            //special bindings
            String cardText = "Production:\n";
            HashMap<TagProvider.ResourceTag, Integer> res = card.getProductions();
            for (TagProvider.ResourceTag tag : TagProvider.ResourceTag.values()) {
                if (res.get(tag) != null)
                {
                    int val = res.get(tag);
                    if(val > 0)
                    {
                        cardText += val + " " + TagProvider.ResToString(tag) + ", ";
                    }
                    else
                    {
                        cardText += val + " " + TagProvider.ResToString(tag) + ", ";
                    }
                }
            }
            cardText += "\nResources:\n";
            res = card.getResources();
            for (TagProvider.ResourceTag tag : TagProvider.ResourceTag.values()) {
                if (res.get(tag) != null)
                {
                    int val = res.get(tag);
                    if(val > 0)
                    {
                        cardText += val + " " + TagProvider.ResToString(tag) + ", ";
                    }
                    else
                    {
                        cardText += val + " " + TagProvider.ResToString(tag) + ", ";
                    }
                }
            }

            text.setText(cardText);

            if(card.getPoints().getBaseScore() != 0) score.setText(String.valueOf(card.getPoints().getBaseScore()));
            else if (card.getPoints().getPointPerResource() != 0) score.setText(String.valueOf(card.getPoints().getResourceCount()) + "/" + String.valueOf(card.getPoints().getPointPerResource()));
            else score.setVisibility(View.INVISIBLE);

            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player.selectCard(card);
                    // trigger something somehow
                }
            });
        }
    }

    /**
     * The adapter for the RecyclerView that displays the Following data.
     */
    private class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardHolder> {

        private final List<Card> rows = new ArrayList<>();

        /**
         * Creates an instance and loads the first page of following data.
         */
        CardRecyclerViewAdapter() {
            loadItems();
        }

        /**
         * Adds new rowss to the list from which the RecyclerView retrieves the rows it displays
         * and notifies the RecyclerView that items have been added.
         *
         * @param newRows the rows to add.
         */
        void addItems(List<Card> newRows) {
            int startInsertPosition = rows.size();
            rows.addAll(newRows);
            this.notifyItemRangeInserted(startInsertPosition, newRows.size());
        }

        /**
         * Adds a single row to the list from which the RecyclerView retrieves the rows it
         * displays and notifies the RecyclerView that an item has been added.
         *
         * @param row the row to add.
         */
        void addItem(Card row) {
            rows.add(row);
            this.notifyItemInserted(rows.size() - 1);
        }

        /**
         * Removes a row from the list from which the RecyclerView retrieves the rows it displays
         * and notifies the RecyclerView that an item has been removed.
         *
         * @param row the row to remove.
         */
        void removeItem(Card row) {
            int position = rows.indexOf(row);
            rows.remove(position);
            this.notifyItemRemoved(position);
        }

        /**
         *  Creates a view holder for a row to be displayed in the RecyclerView or for a message
         *  indicating that new rows are being loaded if we are waiting for rows to load.
         *
         * @param parent the parent view.
         * @param viewType the type of the view (ignored in the current implementation).
         * @return the view holder.
         */
        @NonNull
        @Override
        public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(CardFragmentSelect.this.getContext());
            View view;

            if(viewType == LOADING_DATA_VIEW) {
                view =layoutInflater.inflate(R.layout.loading_row, parent, false);

            } else {
                view = layoutInflater.inflate(R.layout.card_row, parent, false);
            }

            return new CardHolder(view, viewType);
        }

        /**
         * Binds the row at the specified position unless we are currently loading new data. If
         * we are loading new data, the display at that position will be the data loading footer.
         *
         * @param tagHolder the ViewHolder to which the row should be bound.
         * @param position the position (in the list of rows) that contains the row to be
         *                 bound.
         */
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onBindViewHolder(@NonNull CardHolder tagHolder, int position) {
            tagHolder.bindRow(rows.get(position));
        }

        /**
         * Returns the current number of rows available for display.
         * @return the number of rows available for display.
         */
        @Override
        public int getItemCount() {
            return rows.size();
        }

        /**
         * Returns the type of the view that should be displayed for the item currently at the
         * specified position.
         *
         * @param position the position of the items whose view type is to be returned.
         * @return the view type.
         */
        @Override
        public int getItemViewType(int position) {
            return ITEM_VIEW;
        }

        /**
         * Causes the Adapter to display a loading footer and make a request to get more following
         * data.
         */
        void loadItems() {
            addItems(Arrays.asList(GlobalAssets.getInstance(CardFragmentSelect.this.getContext()).getCards()));
        }
    }

    /**
     * A scroll listener that detects when the user has scrolled to the bottom of the currently
     * available data.
     */
    private class CardRecyclerViewPaginationScrollListener extends RecyclerView.OnScrollListener {

        private final LinearLayoutManager layoutManager;

        /**
         * Creates a new instance.
         *
         * @param layoutManager the layout manager being used by the RecyclerView.
         */
        CardRecyclerViewPaginationScrollListener(LinearLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }

        /**
         * Determines whether the user has scrolled to the bottom of the currently available data
         * in the RecyclerView and asks the adapter to load more data if the last load request
         * indicated that there was more data to load.
         *
         * @param recyclerView the RecyclerView.
         * @param dx the amount of horizontal scroll.
         * @param dy the amount of vertical scroll.
         */
        @Override
        public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        }
    }
}
