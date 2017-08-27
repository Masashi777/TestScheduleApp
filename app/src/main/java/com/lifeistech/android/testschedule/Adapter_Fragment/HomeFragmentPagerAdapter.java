package com.lifeistech.android.testschedule.Adapter_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.lifeistech.android.testschedule.Fragments_Detail.ScheduleAllFragment;
import com.lifeistech.android.testschedule.Fragments_Home.HomeFirstFragment;
import com.lifeistech.android.testschedule.Fragments_Home.HomeSecondFragment;
import com.lifeistech.android.testschedule.Fragments_Home.HomeThirdFragment;
import com.lifeistech.android.testschedule.ItemClass.Item;

import java.util.ArrayList;

/**
 * Created by Masashi Hamaguchi on 2017/07/27.
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    private Item item;

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        // Bundleにデータを渡す
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", item);

        // Parcelableにオブジェクトをセット
//        SendTest sendTest = new SendTest();
//        sendTest.setTestObject(test);

        // IntentにParcelableをセット
//        bundle.putParcelable("test", sendTest);


        switch (position) {
            case 0:
                return new HomeFirstFragment();
            case 1:
                HomeSecondFragment frag1 = new HomeSecondFragment();
                frag1.setArguments(bundle);
                return frag1;
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
                return "グラフ";
            case 1:
                return "タスク";
            case 2:
                return "カテゴリ";
        }
        return null;

    }

    public void putItem(Item item) {
        // for FirstFragment and SecondFragment
        this.item = item;

        Log.e("HomeAdapter", "putItem()");
    }

}
