package com.lifeistech.android.testschedule.HomeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lifeistech.android.testschedule.BaseFragment;
import com.lifeistech.android.testschedule.MPAndroid.Example;
import com.lifeistech.android.testschedule.R;
import com.lifeistech.android.testschedule.ScheduleActivity;
import com.lifeistech.android.testschedule.TestClass.Test;
import com.lifeistech.android.testschedule.TestListAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeThirdFragment extends BaseFragment {

    private ListView listView;
    private TestListAdapter testListAdapter;
    private List<Test> testList = new ArrayList<Test>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_home_third, container, false);

        listView = (ListView) view.findViewById(R.id.listView);

        // データの用意
        Example example = new Example();
        testList.add(example.getExam1());
        testList.add(example.getExam2());

        testListAdapter = new TestListAdapter(getActivity().getApplicationContext(), R.layout.list_test, testList);
        listView.setAdapter(testListAdapter);

        // リストへのボタンの配置
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity().getApplicationContext(), ScheduleActivity.class);
                intent.putExtra("test", testList.get(position));
                startActivity(intent);
                Log.e("TAGGG", String.valueOf(testList.size()));

                Snackbar.make(view, "詳細画面へ", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                testList.remove(position);

                testListAdapter.remove(testListAdapter.getItem(position));
                testListAdapter.notifyDataSetChanged();

                return false;
            }
        });

        return view;

    }

}