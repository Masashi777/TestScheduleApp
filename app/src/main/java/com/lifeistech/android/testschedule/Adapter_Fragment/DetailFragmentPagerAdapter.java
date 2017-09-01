package com.lifeistech.android.testschedule.Adapter_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.lifeistech.android.testschedule.Fragments_Detail.SecondFragment;
import com.lifeistech.android.testschedule.Fragments_Detail.ThirdFragment;
import com.lifeistech.android.testschedule.Fragments_Detail.ScheduleAllFragment;
import com.lifeistech.android.testschedule.ItemClass.Category;

import java.util.ArrayList;

/**
 * Created by Masashi Hamaguchi on 2017/07/27.
 */

public class DetailFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Category> categoryList = new ArrayList<Category>();

    public DetailFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            ScheduleAllFragment frag = new ScheduleAllFragment();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("categoryList", categoryList);
//            frag.setArguments(bundle);
            return frag.newInstance();
        } else if (position == 1) {
            return new SecondFragment();
        } else {
            ThirdFragment frag = new ThirdFragment();
            Bundle bundle = new Bundle();
            bundle.putString("categoryName", categoryList.get(position - 2).getCategoryName());
            frag.setArguments(bundle);
            return frag;
//            return new ThirdFragment();
        }

//        switch (position) {
//            case 0:
//                ScheduleAllFragment frag0 = new ScheduleAllFragment();
//                frag0.setArguments(bundle);
//                return frag0.newInstance();
//            case 1:
//                SecondFragment frag1 = new SecondFragment();
//                frag1.setArguments(bundle);
//                return frag1.newInstance();
//            case 2:
//                return new ThirdFragment();
//        }
//        return null;
    }

    @Override
    public int getCount() {
        return categoryList.size() + 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "タスクの割合";
        } else if (position == 1) {
            return "カテゴリ別表示";
        } else {
                return categoryList.get(position - 1).categoryName;
        }

//        switch (position) {
//            case 0:
//                return "All";
//            case 1:
//                return "ページ" + (position + 1);
//            case 2:
//                return "ページ" + (position + 1);
//        }
//        return null;

    }


    public void addCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;

        Log.e("DetailAdapter", "addCategoryList");
    }
}
