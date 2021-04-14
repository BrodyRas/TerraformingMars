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

    private ImageView res1;
    private ImageView res2;
    private ImageView res3;
    private ImageView res4;

    private TextView cid;
    private TextView cost;
    private TextView name;
    private TextView text;
    private TextView score;

    private TextView mdis;
    private TextView tdis;
    private TextView idis;
    private TextView hdis;
    private TextView mamount;
    private TextView tamount;
    private TextView iamount;
    private TextView hamount;

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

        res1 = view.findViewById(R.id.spendMoney);
        res2 = view.findViewById(R.id.spendTitanium);
        res3 = view.findViewById(R.id.spendIron);
        res4 = view.findViewById(R.id.spendHeat);

        cid = view.findViewById(R.id.cardID);
        cost = view.findViewById(R.id.cardCost);
        name = view.findViewById(R.id.cardName);
        text = view.findViewById(R.id.cardText);
        score = view.findViewById(R.id.cardPoints);

        mdis = view.findViewById(R.id.moneyDiscount);
        tdis = view.findViewById(R.id.titaniumValue);
        idis = view.findViewById(R.id.ironValue);
        hdis = view.findViewById(R.id.heatValue);
        mamount = view.findViewById(R.id.moneyAmount);
        tamount = view.findViewById(R.id.titaniumAmount);
        iamount = view.findViewById(R.id.ironAmount);
        hamount = view.findViewById(R.id.heatAmount);

        play = view.findViewById(R.id.playCard);
        dont = view.findViewById(R.id.noPlay);

        tagIcons = Arrays.asList(tag1,tag2,tag3,tag4);

        Card card = player.selectedCard;

        mamount.setText(String.valueOf(card.getCost()));

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

        if (tags.get(TagProvider.CardTag.Building) != null)
        {
            res3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iamount.setText(String.valueOf(Integer.decode((String) iamount.getText())+1));
                    mamount.setText(String.valueOf(Integer.decode((String) mamount.getText())-Integer.decode((String) idis.getText())));
                }
            });
        }
        else
        {
            idis.setVisibility(View.GONE);
            res3.setVisibility(View.GONE);
            iamount.setVisibility(View.GONE);
        }

        if (tags.get(TagProvider.CardTag.Space) != null)
        {
            res2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tamount.setText(String.valueOf(Integer.decode((String) tamount.getText())+1));
                    mamount.setText(String.valueOf(Integer.decode((String) mamount.getText())-Integer.decode((String) tdis.getText())));
                }
            });
        }
        else
        {
            tdis.setVisibility(View.GONE);
            res2.setVisibility(View.GONE);
            tamount.setVisibility(View.GONE);
        }

        if (player.getTableau().getCorp().getName().equals("Helion"))
        {
            res4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hamount.setText(String.valueOf(Integer.decode((String) hamount.getText())+1));
                    mamount.setText(String.valueOf(Integer.decode((String) mamount.getText())-1));
                }
            });
        }
        else
        {
            hdis.setVisibility(View.GONE);
            res4.setVisibility(View.GONE);
            hamount.setVisibility(View.GONE);
        }

        res1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mamount.setText(String.valueOf(card.getCost()));
                tamount.setText("0");
                iamount.setText("0");
                hamount.setText("0");
            }
        });

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
                if(playCard(card)) observer.cardPlayed();
                else; //toast
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

    private boolean playCard(Card card)
    {
        //first try to pay
        if (player.tableau.getResource(TagProvider.ResourceTag.Credits) >= Integer.decode((String) mamount.getText())
        && player.tableau.getResource(TagProvider.ResourceTag.Titanium) >= Integer.decode((String) tamount.getText())
        && player.tableau.getResource(TagProvider.ResourceTag.Iron) >= Integer.decode((String) iamount.getText())
        && player.tableau.getResource(TagProvider.ResourceTag.Heat) >= Integer.decode((String) hamount.getText()))
        {
            player.tableau.updateResource(TagProvider.ResourceTag.Credits, -Integer.decode((String) mamount.getText()));
            player.tableau.updateResource(TagProvider.ResourceTag.Titanium, -Integer.decode((String) tamount.getText()));
            player.tableau.updateResource(TagProvider.ResourceTag.Iron, -Integer.decode((String) iamount.getText()));
            player.tableau.updateResource(TagProvider.ResourceTag.Heat, -Integer.decode((String) hamount.getText()));

            HashMap<TagProvider.CardTag, Integer> tags = card.getTags();
            for (TagProvider.CardTag tag : TagProvider.CardTag.values()) {
                if (tags.get(tag) != null)
                {
                    int count = tags.get(tag);
                    while (count > 0)
                    {
                        player.tableau.updateMyTags(tag, 1);
                        --count;
                    }
                }
            }

            HashMap<TagProvider.ResourceTag, Integer> res = card.getProductions();
            for (TagProvider.ResourceTag tag : TagProvider.ResourceTag.values()) {
                if (res.get(tag) != null)
                {
                    int val = res.get(tag);
                    player.tableau.updateProduction(tag, val);
                }
            }

            res = card.getResources();
            for (TagProvider.ResourceTag tag : TagProvider.ResourceTag.values()) {
                if (res.get(tag) != null)
                {
                    int val = res.get(tag);
                    player.tableau.updateResource(tag, val);
                }
            }
            return true;
        }
        else return false;
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
