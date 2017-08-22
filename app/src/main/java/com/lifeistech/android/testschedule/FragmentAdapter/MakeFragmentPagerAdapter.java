package com.lifeistech.android.testschedule.FragmentAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

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
//        CardFragment cardFragment = new CardFragment();
//        FragmentTransaction transaction = cardFragment.getFragmentManager().beginTransaction();
//        transaction.add(cardFragment, String.valueOf(i));
//        transaction.commit();
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
