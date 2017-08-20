package com.lifeistech.android.testschedule.FragmentAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lifeistech.android.testschedule.CardFragment;

/**
 * Created by Masashi Hamaguchi on 2017/07/17.
 */

public class MakeFragmentPagerAdapter extends FragmentPagerAdapter {

    public MakeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new CardFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position);
    }
}
