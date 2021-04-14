package TMars.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import TMars.model.Card;
import TMars.model.Player;
import TMars.providers.TagProvider;
import edu.byu.cs.tweeter.R;

public class CardFragmentPlay extends Fragment {

    private final Player player;
    private final CardInterface observer;
    private Activity mActivity;

    private ImageView tag1;
    private ImageView tag2;
    private ImageView tag3;
    private ImageView tag4;
    private List<ImageView> tagIcons;

    private TextView cid;
    private TextView cost;
    private TextView name;
    private TextView text;
    private TextView score;

    private Button play;
    private Button dont;

    private CardFragmentPlay(Player player, CardInterface observer)
    {
        this.player = player;
        this.observer = observer;
    }

    /**
     * Creates an instance of the fragment and places the user and auth token in an arguments
     * bundle assigned to the fragment.
     *
     * @param player the current player.
     * @return the fragment.
     */
    public static CardFragmentPlay newInstance(Player player, CardInterface observer) {
        CardFragmentPlay fragment = new CardFragmentPlay(player, observer);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cardselected, container, false);

        tag1 = view.findViewById(R.id.tag1);
        tag2 = view.findViewById(R.id.tag2);
        tag3 = view.findViewById(R.id.tag3);
        tag4 = view.findViewById(R.id.tag4);

        cid = view.findViewById(R.id.cardID);
        cost = view.findViewById(R.id.cardCost);
        name = view.findViewById(R.id.cardName);
        text = view.findViewById(R.id.cardText);
        score = view.findViewById(R.id.cardPoints);

        play = view.findViewById(R.id.playCard);
        dont = view.findViewById(R.id.noPlay);

        tagIcons = Arrays.asList(tag1,tag2,tag3,tag4);

        Card card = player.selectedCard;

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
        String cardText = "";//"Production:\n";
        Boolean first = true;
        HashMap<TagProvider.ResourceTag, Integer> res = card.getProductions();
        for (TagProvider.ResourceTag tag : TagProvider.ResourceTag.values()) {
            if (res.get(tag) != null)
            {
                if (first) {
                    cardText += "Production:\n";
                    first = false;
                }
                int val = res.get(tag);
                if(val > 0)
                {
                    cardText += "+" + val + " " + TagProvider.ResToString(tag) + ", ";
                }
                else
                {
                    cardText += val + " " + TagProvider.ResToString(tag) + ", ";
                }
            }
        }
        //cardText += "\nResources:\n";
        first = true;
        res = card.getResources();
        for (TagProvider.ResourceTag tag : TagProvider.ResourceTag.values()) {
            if (res.get(tag) != null)
            {
                if (first) {
                    cardText += "\nResources:\n";
                    first = false;
                }
                int val = res.get(tag);
                if(val > 0)
                {
                    cardText += "+" + val + " " + TagProvider.ResToString(tag) + ", ";
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

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adjust tableau
                observer.cardPlayed();
            }
        });

        dont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observer.cardPlayed();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            mActivity =(Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    public void refresh(Fragment replace)
    {
        if(mActivity != null) getActivity().getSupportFragmentManager().beginTransaction().detach(this).attach(replace).commit();
    }
}
