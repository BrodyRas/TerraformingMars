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
import java.util.List;

import TMars.model.Player;
import TMars.model.TableauRow;
import TMars.model.TagRow;
import TMars.presenters.TableauPresenter;
import edu.byu.cs.tweeter.R;

/**
 * The fragment that displays on the 'Following' tab.
 */
public class TagFragment extends Fragment {

    private static final String LOG_TAG = "TagFragment";
    private static final String PLAYER_KEY = "PlayerKey";

    private static final int LOADING_DATA_VIEW = 0;
    private static final int ITEM_VIEW = 1;
    private final TableauPresenter presenter;

    private TagRecyclerViewAdapter tagRecyclerViewAdapter;

    private TagFragment(TableauPresenter tableauPresenter) {
        presenter = tableauPresenter;
    }

    /**
     * Creates an instance of the fragment and places the user and auth token in an arguments
     * bundle assigned to the fragment.
     *
     * @param player the current player.
     * @return the fragment.
     */
    public static TagFragment newInstance(Player player) {
        TagFragment fragment = new TagFragment(new TableauPresenter(player));
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scrollview, container, false);

        RecyclerView tagRecyclerView = view.findViewById(R.id.scrollingRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        tagRecyclerView.setLayoutManager(layoutManager);

        tagRecyclerViewAdapter = new TagRecyclerViewAdapter();
        tagRecyclerView.setAdapter(tagRecyclerViewAdapter);

        tagRecyclerView.addOnScrollListener(new TagRecyclerViewPaginationScrollListener(layoutManager));

        return view;
    }


    /**
     * The ViewHolder for the RecyclerView that displays the Following data.
     */
    private class TagHolder extends RecyclerView.ViewHolder {

        private final ImageView icon;
        private final TextView quantity;
        private final TextView production;
        private final Button incMe;
        private final Button decMe;
        private final Button incO;
        private final Button decO;

        /**
         * Creates an instance and sets an OnClickListener for the user's row.
         *
         * @param itemView the view on which the user will be displayed.
         */
        TagHolder(@NonNull View itemView, int viewType) {
            super(itemView);

            if(viewType == ITEM_VIEW) {
                icon = itemView.findViewById(R.id.tagImage);
                quantity = itemView.findViewById(R.id.quantity);
                production = itemView.findViewById(R.id.production);
                incMe = itemView.findViewById(R.id.get);
                decMe = itemView.findViewById(R.id.use);
                incO = itemView.findViewById(R.id.increase);
                decO = itemView.findViewById(R.id.decrease);

            } else {
                icon = null;
                quantity = null;
                production = null;
                incMe = null;
                decMe = null;
                incO = null;
                decO = null;
            }
        }

        /**
         * Binds the user's data to the view.
         *
         * @param row the row.
         */
        @RequiresApi(api = Build.VERSION_CODES.M)
        void bindRow(TagRow row) {
            icon.setImageResource(row.iconID);

            incMe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //quantity.setText(String.valueOf(Integer.decode(quantity.getText().toString())+1));
                    quantity.setText(presenter.tapMe(row.tag,1));
                }
            });

            decMe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //quantity.setText(String.valueOf(Integer.decode(quantity.getText().toString())-1));
                    quantity.setText(presenter.tapMe(row.tag,-1));
                }
            });

            incO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //production.setText(String.valueOf(Integer.decode(production.getText().toString())+1));
                    production.setText(presenter.tapO(row.tag,1));
                }
            });

            decO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //production.setText(String.valueOf(Integer.decode(production.getText().toString())-1));
                    production.setText(presenter.tapO(row.tag,-1));
                }
            });
            //TODO: Set with current value. Fix tags to be circles
            quantity.setText("0");
            production.setText("0");
        }
    }

    /**
     * The adapter for the RecyclerView that displays the Following data.
     */
    private class TagRecyclerViewAdapter extends RecyclerView.Adapter<TagHolder> {

        private final List<TagRow> rows = new ArrayList<>();

        /**
         * Creates an instance and loads the first page of following data.
         */
        TagRecyclerViewAdapter() {
            loadItems();
        }

        /**
         * Adds new rowss to the list from which the RecyclerView retrieves the rows it displays
         * and notifies the RecyclerView that items have been added.
         *
         * @param newRows the rows to add.
         */
        void addItems(List<TagRow> newRows) {
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
        void addItem(TagRow row) {
            rows.add(row);
            this.notifyItemInserted(rows.size() - 1);
        }

        /**
         * Removes a row from the list from which the RecyclerView retrieves the rows it displays
         * and notifies the RecyclerView that an item has been removed.
         *
         * @param row the row to remove.
         */
        void removeItem(TagRow row) {
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
        public TagHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(TagFragment.this.getContext());
            View view;

            if(viewType == LOADING_DATA_VIEW) {
                view =layoutInflater.inflate(R.layout.loading_row, parent, false);

            } else {
                view = layoutInflater.inflate(R.layout.tag_row, parent, false);
            }

            return new TagHolder(view, viewType);
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
        public void onBindViewHolder(@NonNull TagHolder tagHolder, int position) {
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
            List<TagRow> newRows = new ArrayList<>();
            for(int i = 0; i < 12; ++i)
            {
                newRows.add(new TagRow(i));
            }
            addItems(newRows);
        }
    }

    /**
     * A scroll listener that detects when the user has scrolled to the bottom of the currently
     * available data.
     */
    private class TagRecyclerViewPaginationScrollListener extends RecyclerView.OnScrollListener {

        private final LinearLayoutManager layoutManager;

        /**
         * Creates a new instance.
         *
         * @param layoutManager the layout manager being used by the RecyclerView.
         */
        TagRecyclerViewPaginationScrollListener(LinearLayoutManager layoutManager) {
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
