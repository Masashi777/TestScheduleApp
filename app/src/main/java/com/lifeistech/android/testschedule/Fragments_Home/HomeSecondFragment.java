package com.lifeistech.android.testschedule.Fragments_Home;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.lifeistech.android.testschedule.EditActivity;
import com.lifeistech.android.testschedule.ItemClass.Item;
import com.lifeistech.android.testschedule.Adapter_ListView.ItemListAdapter;
import com.lifeistech.android.testschedule.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class HomeSecondFragment extends BaseFragment {

    private ListView listView;
    private Button newBtn;
    private ArrayList<Item> itemList = new ArrayList<Item>();

    private ItemListAdapter itemListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_home_second, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        newBtn = (Button) view.findViewById(R.id.newBtn);

        // データの取得
        loadItems();

        try {
            Item item;

            Bundle bundle = getArguments();
            item = (Item) bundle.getSerializable("item");

            itemList.add(item);
        } catch (Exception e){

        }


        /**
         * ArrayListはシリアライズに対応していない
         */

        itemListAdapter = new ItemListAdapter(getActivity().getApplicationContext(), R.layout.list_item, itemList);
        listView.setAdapter(itemListAdapter);

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

        // newBtnへのセット
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), EditActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //要素群の書き込み
        saveItems();

    }

    //要素群の書き込み
    private void saveItems() {
        //ArrayListをJSONに変換
        String json = list2json(itemList);

        //プリファレンスへの書き込み
        SharedPreferences pref = getActivity().getSharedPreferences(
                "ToDoApp", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("itemList", json);
        editor.commit();
    }

    //要素群の読み込み
    private void loadItems() {
        //プリファレンスからの読み込み
        SharedPreferences pref = getActivity().getSharedPreferences(
                "ToDoApp", MODE_PRIVATE);
        String json = pref.getString("itemList","");

        //JSONをArrayListに変換
        itemList = items2list(json);
    }

    //ArrayListをJSONに変換(5)
    private String list2json(ArrayList<Item> items) {
        try {
            JSONArray array = new JSONArray();
            for (Item item : items) {
                JSONObject obj = new JSONObject();
                obj.put("title", item.itemName);
                obj.put("category", item.category);
                obj.put("checked", item.checked);
                array.put(obj);
            }
            return array.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    //JSONをArrayListに変換(6)
    private ArrayList<Item> items2list(String json) {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Item item = new Item();
                item.itemName = obj.getString("title");
                item.category = obj.getInt("category");
                item.checked = obj.getBoolean("checked");
                items.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

}