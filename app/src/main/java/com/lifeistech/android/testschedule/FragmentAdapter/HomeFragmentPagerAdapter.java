package com.lifeistech.android.testschedule.FragmentAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lifeistech.android.testschedule.HomeFragments.HomeFirstFragment;
import com.lifeistech.android.testschedule.HomeFragments.HomeSecondFragment;
import com.lifeistech.android.testschedule.HomeFragments.HomeThirdFragment;

/**
 * Created by Masashi Hamaguchi on 2017/07/27.
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new HomeFirstFragment();
            case 1:
                return new HomeSecondFragment();
            case 2:
                return new HomeThirdFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "First";
            case 1:
                return "Second";
            case 2:
                return "Third";
        }
        return null;

    }

}
