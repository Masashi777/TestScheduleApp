package com.lifeistech.android.testschedule.Fragments_Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lifeistech.android.testschedule.BaseFragment;
import com.lifeistech.android.testschedule.CategoryEditActivity;
import com.lifeistech.android.testschedule.ItemClass.Item;
import com.lifeistech.android.testschedule.Adapter_ListView.ItemListAdapter;
import com.lifeistech.android.testschedule.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class HomeFrag_Second extends BaseFragment {

    private ListView itemListView;
    private Button newBtn;
    private ArrayList<String> categoryList = new ArrayList<String>();
    private ArrayList<Item> itemList = new ArrayList<Item>();

    private ItemListAdapter itemListAdapter;

    // Realm
    Realm realm;
    RealmResults<Item> result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_home_second, container, false);

        itemListView = (ListView) view.findViewById(R.id.itemListView);
        newBtn = (Button) view.findViewById(R.id.newBtn);

        // データの取得
        realm = Realm.getInstance(Realm.getDefaultConfiguration());
        try {
            result = realm.where(Item.class).findAll();
        } catch (Exception e) {

        }

        for (int i = 0; i < result.size(); i++) {
            if (categoryList.lastIndexOf(result.get(i)) == 0) {
                categoryList.add(result.get(i).getCategory());
            }
        }

        // リストへのボタンの配置
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), CategoryEditActivity.class);
                intent.putExtra("category", categoryList.get(position));
                startActivity(intent);

            }
        });

        itemListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                itemList.remove(position);
                itemListAdapter.notifyDataSetChanged();

                Snackbar.make(view, "You tapped LONG " + itemListView.getSelectedItem() + "on the ListView.", Snackbar.LENGTH_SHORT)
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

        // Adapter
//        itemListAdapter = new ItemListAdapter(getContext(), R.layout.list_item, itemList);
//        itemListView.setAdapter(itemListAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


}