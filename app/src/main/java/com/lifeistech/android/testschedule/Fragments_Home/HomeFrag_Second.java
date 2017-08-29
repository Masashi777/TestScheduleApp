package com.lifeistech.android.testschedule.Fragments_Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lifeistech.android.testschedule.BaseFragment;
import com.lifeistech.android.testschedule.EditActivity;
import com.lifeistech.android.testschedule.ItemClass.Category;
import com.lifeistech.android.testschedule.ItemClass.Item;
import com.lifeistech.android.testschedule.Adapter_ListView.ItemListAdapter;
import com.lifeistech.android.testschedule.R;

import java.util.ArrayList;

import static com.lifeistech.android.testschedule.GsonConverter.loadCategories;
import static com.lifeistech.android.testschedule.GsonConverter.loadItems;
import static com.lifeistech.android.testschedule.GsonConverter.saveItems;

public class HomeFrag_Second extends BaseFragment {

    private ListView listView;
    private Button newBtn;
    private ArrayList<Category> categoryList = new ArrayList<Category>();
    private ArrayList<Item> itemList = new ArrayList<Item>();

    private ItemListAdapter itemListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_home_second, container, false);

        listView = (ListView) view.findViewById(R.id.itemListView);
        newBtn = (Button) view.findViewById(R.id.newBtn);

        // データの取得
        categoryList = loadCategories(getContext());
        for (int n = 0; n < categoryList.size(); n++) {
            for (int m = 0; m < categoryList.get(n).getItemList().size(); m++) {
                itemList.add(categoryList.get(n).getItemList().get(m));
            }
        }

        /**
         * ArrayListはシリアライズに対応していない
         */


        // リストへのボタンの配置
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                saveItems(getActivity(), itemList);
                Intent intent = new Intent(getActivity().getApplicationContext(), EditActivity.class);
                intent.putExtra("item", itemList.get(position));
                intent.putExtra("position", position);
                startActivity(intent);

                Log.e("itemList", "selected");
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

        // Adapter
        itemListAdapter = new ItemListAdapter(getActivity().getApplicationContext(), R.layout.list_item, itemList);
        listView.setAdapter(itemListAdapter);

        // newBtnへのセット
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loadCategories(getContext()).size() == 0) {
                    // NO CATEGORIES
                    Snackbar.make(v, "カテゴリを作成してください", Snackbar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getContext(), EditActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //要素群の書き込み
        saveItems(getActivity(), itemList);

    }


}