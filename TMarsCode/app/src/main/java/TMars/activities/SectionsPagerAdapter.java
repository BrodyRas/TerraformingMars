package TMars.activities;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import TMars.fragments.CardFragmentPlay;
import TMars.fragments.CardFragmentSelect;
import TMars.fragments.CardInterface;
import TMars.fragments.HelpFragment;
import TMars.fragments.PlaceholderFragment;
import TMars.fragments.TableauFragment;
import TMars.fragments.TagFragment;
import TMars.model.Card;
import TMars.model.Player;
import edu.byu.cs.tweeter.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to one of the sections/tabs/pages
 * of the Main Activity.
 */
class SectionsPagerAdapter extends FragmentStatePagerAdapter implements CardInterface {

    private static final int TABLEAU_FRAGMENT_POSITION = 0;
    private static final int TAG_FRAGMENT_POSITION = 1;
    private static final int CARD_FRAGMENT_POSITION = 2;
    private static final int HELP_FRAGMENT_POSITION = 3;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tableauTabTitle, R.string.tagTabTitle, R.string.cardTabTitle, R.string.helpTabTitle};
    private Fragment[] tabs = new Fragment[]{null,null,null,null};
    private final Context mContext;
    private final Player player;
    private final FragmentManager mFragmentManager;

    public SectionsPagerAdapter(Context context, FragmentManager fm, Player player) {
        super(fm);
        mFragmentManager = fm;
        mContext = context;
        this.player = player;
    }

    public void refresh()
    {
        ((TableauFragment) tabs[TABLEAU_FRAGMENT_POSITION]).refresh();
        ((TagFragment) tabs[TAG_FRAGMENT_POSITION]).refresh();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == TABLEAU_FRAGMENT_POSITION) {
            tabs[TABLEAU_FRAGMENT_POSITION] = TableauFragment.newInstance(player);
            return tabs[TABLEAU_FRAGMENT_POSITION];
        } else if (position == TAG_FRAGMENT_POSITION)
        {
            tabs[TAG_FRAGMENT_POSITION] = TagFragment.newInstance(player);
            return tabs[TAG_FRAGMENT_POSITION];
        } else if (position == HELP_FRAGMENT_POSITION)
        {
            tabs[HELP_FRAGMENT_POSITION] = HelpFragment.newInstance();
            return tabs[HELP_FRAGMENT_POSITION];
        } else if (position == CARD_FRAGMENT_POSITION)
        {
            //check a flag in player to know which version of card to select.
            if (!player.cardSelected) tabs[CARD_FRAGMENT_POSITION] = CardFragmentSelect.newInstance(this);
            else tabs[CARD_FRAGMENT_POSITION] = CardFragmentPlay.newInstance(player, this);
            return tabs[CARD_FRAGMENT_POSITION];
        } else
        {
            return PlaceholderFragment.newInstance(position + 1);
        }
    }

    @Override
    public int getItemPosition(Object object)
    {
        if ((object instanceof CardFragmentSelect && player.cardSelected) ||
                (object instanceof  CardFragmentPlay && !player.cardSelected)) return POSITION_NONE;
        else return POSITION_UNCHANGED;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }

    @Override
    public void cardSelected(Card card) {
        player.selectCard(card);
        mFragmentManager.beginTransaction().remove(tabs[CARD_FRAGMENT_POSITION]).commit();
        notifyDataSetChanged();
        refresh();
    }

    @Override
    public void cardPlayed() {
        player.playedCard();
        mFragmentManager.beginTransaction().remove(tabs[CARD_FRAGMENT_POSITION]).commit();
        notifyDataSetChanged();
        refresh();
    }
}