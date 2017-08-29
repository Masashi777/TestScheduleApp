package com.lifeistech.android.testschedule.Adapter_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.lifeistech.android.testschedule.Fragments_Home.HomeFrag_First;
import com.lifeistech.android.testschedule.Fragments_Home.HomeFrag_Second;
import com.lifeistech.android.testschedule.Fragments_Home.HomeFrag_Third;
import com.lifeistech.android.testschedule.ItemClass.Item;

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
                return new HomeFrag_First();
            case 1:
                HomeFrag_Second frag1 = new HomeFrag_Second();
                frag1.setArguments(bundle);
                return frag1;
            case 2:
                return new HomeFrag_Third();
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
        // for FirstFragment and DetailFrag_Second
        this.item = item;

        Log.e("HomeAdapter", "putItem()");
    }

}
