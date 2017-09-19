package com.lifeistech.android.testschedule.Adapter_Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lifeistech.android.testschedule.Fragments_Home.HomeFrag_First;
import com.lifeistech.android.testschedule.Fragments_Home.HomeFrag_Second;

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
                return new HomeFrag_First();
            case 1:
                return new HomeFrag_Second();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "グラフ";
            case 1:
                return "タスク";
            case 2:
                return "カテゴリ";
        }
        return null;

    }

}
