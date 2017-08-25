package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lifeistech.android.testschedule.Adapter_Fragment.DetailFragmentPagerAdapter;
import com.lifeistech.android.testschedule.ItemClass.Category;

import java.util.ArrayList;

public class DetailActivity extends BaseActivity {

    private ViewPager mViewPager;

    private ArrayList<Category> categoryList = new ArrayList<Category>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // タイトルの設定
        setTitle("スケジュール");

        // ひもづけ
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        // データの取得
        Intent intent = getIntent();
//        test = (Test) intent.getSerializableExtra("test");
//        Log.e("DetailActivity", test.getComment());
//        Log.e("DetailActivity", String.valueOf(test.getDateList().get(0).getMonth()));


        setViews();
    }

    private void setViews() {

        // データの処理
        String[] categories = {"遊び", "勉強", "マンガを読む", "部活", "趣味", "テスト勉強"};
        boolean[] checkList = {false, true, false, true, false, true};
        for (int i = 0; i < categories.length; i++) {
            categoryList.add(Category.addCategory(categories[i], checkList[i], "0"));
        }
        // PagerAdapterの作成
        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailFragmentPagerAdapter adapter = new DetailFragmentPagerAdapter(fragmentManager);
        adapter.addCategoryList(categoryList);

        mViewPager.setAdapter(adapter);

        // Tabレイアウトの設定
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

    }

}
