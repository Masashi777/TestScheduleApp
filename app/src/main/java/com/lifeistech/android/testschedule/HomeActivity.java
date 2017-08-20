package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lifeistech.android.testschedule.MPAndroid.Example;
import com.lifeistech.android.testschedule.TestClass.Test;

import java.util.Arrays;
import java.util.List;

public class HomeActivity extends BaseActivity {

    SharedPreferences pref;

    public List<Test> testList;

    ListView homeList;
    TestListAdapter testListAdapter;

    Example example = new Example();

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


        // データを復活
//        try {
//            pref = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
//            Gson gson = new Gson();
//            testList = gson.fromJson(pref.getString("testGson", ""), new TypeToken<List<Test>>(){}.getType());
//
//        } catch (Exception e) {
//
//        }

        //保存するとき
        //GsonでUserをJSON文字列に変換して保存する
//        Gson gson = new Gson();
//        gson.toJson(testList);
//        pref.edit().putString("testGson", gson.toJson(testList)).commit();

        // タイトルの設定
        setTitle("テスト一覧");

        //オリジナル

        homeList = (ListView) findViewById(R.id.homeList);

        testList = Arrays.asList(example.getExam1(), example.getExam2());

//        testList.add(example.getExam1());
//        testList.add(example.getExam2());

        Log.e("TAGGG", String.valueOf(testList.size()));


        testListAdapter = new TestListAdapter(this, R.layout.list_test, testList);
        homeList.setAdapter(testListAdapter);


        // リストへのボタンの配置
        homeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                intent.putExtra("test", testList.get(position));
                startActivity(intent);
                Log.e("TAGGG", String.valueOf(testList.size()));

                Snackbar.make(view, "詳細画面へ", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        homeList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                testList.remove(position);

                testListAdapter = new TestListAdapter(getApplicationContext(), R.layout.list_test, testList);
                homeList.setAdapter(testListAdapter);

                return false;
            }
        });
    }

}
