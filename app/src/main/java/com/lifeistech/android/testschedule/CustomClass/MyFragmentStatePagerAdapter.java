package com.lifeistech.android.testschedule.CustomClass;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.lifeistech.android.testschedule.CardFragment;

import java.util.List;

/**
 * Created by Masashi Hamaguchi on 2017/07/17.
 */

public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    public MyFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        return new CardFragment();

//        switch(i){
//            case 0:
//                return new Fragment0();
//            case 1:
//                return new Fragment1();
//            default:
//                return new Fragment2();
//        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}
