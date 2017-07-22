package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends NavigationActivity {
    ListView homeList;
    List<TestList> mTestLists;
    TestListAdapter testListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MakeActivity.class);
                startActivity(intent);
            }
        });

        //オリジナル

        homeList = (ListView) findViewById(R.id.homeList);

        // テストの要素
        mTestLists = new ArrayList<TestList>();
        mTestLists.add(new TestList("●月✖日", "○○のテスト"));
        mTestLists.add(new TestList("TestDate", "TestName"));

        testListAdapter = new TestListAdapter(this, R.layout.test_list, mTestLists);

        homeList.setAdapter(testListAdapter);

        homeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "詳細画面へ", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

    }


}
