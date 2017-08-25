package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lifeistech.android.testschedule.FragmentAdapter.HomeFragmentPagerAdapter;

public class HomeActivity extends BaseActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Gson gson = new Gson();
//                gson.toJson(testList);
//                pref.edit().putString("SaveTestList", gson.toJson(testList)).commit();

                Intent intent = new Intent(getApplicationContext(), MakeActivity.class);
                startActivity(intent);
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
        setTitle("タスク！");

        // ひもづけ
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setViews();
    }

    private void setViews() {

        // PagerAdapterの作成
        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(fragmentManager);

        viewPager.setAdapter(adapter);

        // Tabレイアウトの設定
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onDestroy() {
//        Gson gson = new Gson();
//        gson.toJson(testList);
//        pref.edit().putString("SaveTestList", gson.toJson(testList)).commit();
    }

}