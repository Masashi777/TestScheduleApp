package com.lifeistech.android.testschedule.Adapter_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.lifeistech.android.testschedule.Fragments_Detail.DetailFrag_All;
import com.lifeistech.android.testschedule.Fragments_Detail.DetailFrag_Second;
import com.lifeistech.android.testschedule.Fragments_Detail.DetailFrag_Categry;
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
            DetailFrag_All frag = new DetailFrag_All();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("categoryList", categoryList);
//            frag.setArguments(bundle);
            return frag.newInstance();
        } else if (position == 1) {
            return new DetailFrag_Second();
        } else {
            DetailFrag_Categry frag = new DetailFrag_Categry();
            Bundle bundle = new Bundle();
            bundle.putString("categoryName", categoryList.get(position - 2).getCategoryName());
            frag.setArguments(bundle);
            return frag;
//            return new DetailFrag_Categry();
        }

//        switch (position) {
//            case 0:
//                DetailFrag_All frag0 = new DetailFrag_All();
//                frag0.setArguments(bundle);
//                return frag0.newInstance();
//            case 1:
//                DetailFrag_Second frag1 = new DetailFrag_Second();
//                frag1.setArguments(bundle);
//                return frag1.newInstance();
//            case 2:
//                return new DetailFrag_Categry();
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
            return "All";
        } else if (position == 1) {
            return "chart";
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
