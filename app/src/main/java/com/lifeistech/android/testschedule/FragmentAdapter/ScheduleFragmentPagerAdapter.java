package com.lifeistech.android.testschedule.FragmentAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.lifeistech.android.testschedule.MainFragment;
import com.lifeistech.android.testschedule.ScheduleFragments.ScheduleAllFragment;
import com.lifeistech.android.testschedule.ScheduleFragments.SecondFragment;
import com.lifeistech.android.testschedule.TestClass.Test;

/**
 * Created by Masashi Hamaguchi on 2017/07/27.
 */

public class ScheduleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Test test;

    public ScheduleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        // Bundleにデータを渡す
        Bundle bundle = new Bundle();
        bundle.putSerializable("test", test);

        // Parcelableにオブジェクトをセット
//        SendTest sendTest = new SendTest();
//        sendTest.setTestObject(test);

        // IntentにParcelableをセット
//        bundle.putParcelable("test", sendTest);


        switch (position) {
            case 0:
                ScheduleAllFragment frag0 = new ScheduleAllFragment();
                frag0.setArguments(bundle);
                return frag0.newInstance();
            case 1:
                SecondFragment frag1 = new SecondFragment();
                frag1.setArguments(bundle);
                return frag1.newInstance();
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


    public void addTest(Test test) {
        this.test = test;

        Log.e("Adapter", this.test.getTestName());
    }
}
