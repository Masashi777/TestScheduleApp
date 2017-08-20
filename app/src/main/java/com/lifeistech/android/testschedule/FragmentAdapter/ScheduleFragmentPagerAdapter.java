package com.lifeistech.android.testschedule.FragmentAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lifeistech.android.testschedule.MainFragment;
import com.lifeistech.android.testschedule.ScheduleFragments.ScheduleAllFragment;
import com.lifeistech.android.testschedule.ScheduleFragments.SecondFragment;

/**
 * Created by Masashi Hamaguchi on 2017/07/27.
 */

public class ScheduleFragmentPagerAdapter extends FragmentPagerAdapter {

    public ScheduleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ScheduleAllFragment.newInstance();
            case 1:
                return SecondFragment.newInstance();
            case 2:
                return MainFragment.newInstance(android.R.color.holo_red_dark);
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
                return "All";
            case 1:
                return "ページ" + (position + 1);
            case 2:
                return "ページ" + (position + 1);
        }
        return null;

    }
}
