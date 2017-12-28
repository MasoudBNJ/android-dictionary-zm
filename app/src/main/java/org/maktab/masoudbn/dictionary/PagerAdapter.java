package org.maktab.masoudbn.dictionary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by user on 2017/12/22.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    int numOfTabs;
    WordListFragment wordListFragment;


    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
//        wordListFragment.updateUI();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SearchFragment.newInstance();
            case 1:
                return new AddFragment();
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return numOfTabs;
    }
}
