package TMars.activities;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import TMars.fragments.PlaceholderFragment;
import TMars.fragments.TableauFragment;
import TMars.fragments.TagFragment;
import TMars.model.Player;
import edu.byu.cs.tweeter.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to one of the sections/tabs/pages
 * of the Main Activity.
 */
class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private static final int TABLEAU_FRAGMENT_POSITION = 0;
    private static final int TAG_FRAGMENT_POSITION = 1;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tableauTabTitle, R.string.tagTabTitle, R.string.cardTabTitle, R.string.actionTabTitle};
    private Fragment[] tabs = new Fragment[]{null,null,null,null};
    private final Context mContext;
    private final Player player;

    public SectionsPagerAdapter(Context context, FragmentManager fm, Player player) {
        super(fm);
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
        }
        else
        {
            return PlaceholderFragment.newInstance(position + 1);
        }
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
}