package com.lifeistech.android.testschedule.HomeFragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lifeistech.android.testschedule.BaseFragment;
import com.lifeistech.android.testschedule.R;

import java.util.ArrayList;

public class HomeSecondFragment extends BaseFragment {

    private ListView listView;
    private ArrayList<String> stringArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_home_second, container, false);

        listView = (ListView) view.findViewById(R.id.listView);

        // データの用意
        final ArrayAdapter<String> adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1);

        for (int i = 0; i < 20; i++) {
            adapter.add("Content " + String.valueOf(i + 1));
        }

        listView.setAdapter(adapter);

        // リストへのボタンの配置
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Snackbar.make(view, "You tapped " + listView.getSelectedItem() + "on the ListView.", Snackbar.LENGTH_SHORT)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity().getApplicationContext(), "Are You Ready?", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    Snackbar.make(view, "You tapped LONG " + listView.getSelectedItem() + "on the ListView.", Snackbar.LENGTH_SHORT)
                            .setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getActivity().getApplicationContext(), "YEAH~~!!!", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                return false;
            }
        });

        return view;

    }

}