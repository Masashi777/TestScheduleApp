package com.lifeistech.android.testschedule.Fragments_Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lifeistech.android.testschedule.Adapter_ListView.CategoryListAdapter;
import com.lifeistech.android.testschedule.Adapter_ListView.ItemListAdapter;
import com.lifeistech.android.testschedule.BaseFragment;
import com.lifeistech.android.testschedule.ItemClass.Category;
import com.lifeistech.android.testschedule.ItemClass.Item;
import com.lifeistech.android.testschedule.R;
import com.lifeistech.android.testschedule.DetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class HomeThirdFragment extends BaseFragment {

    private ListView listView;
    private CategoryListAdapter categoryListAdapter;
    private ArrayList<Category> categoryList = new ArrayList<Category>();

    private String[] categories = {"遊び", "勉強", "マンガを読む", "部活", "趣味", "テスト勉強"};
    private boolean[] checkList = {false, true, false, true, false, true};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 臨時データ
         */
        for (int i = 0; i < categories.length; i++) {
            categoryList.add(Category.addCategory(categories[i], checkList[i], "0"));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_home_third, container, false);

        listView = (ListView) view.findViewById(R.id.listView);

        // データの用意
//        Bundle bundle = getArguments();
//        categoryList = (ArrayList<Category>) bundle.getSerializable("categoryList", "");



        // Adapter
        categoryListAdapter = new CategoryListAdapter(getActivity().getApplicationContext(), R.layout.list_category, categoryList);
        listView.setAdapter(categoryListAdapter);

        // リストへのボタンの配置
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity().getApplicationContext(), DetailActivity.class);
                intent.putExtra("category", categoryList.get(position));
                startActivity(intent);
                Log.e("TAGGG", String.valueOf(categoryList.size()));

                Snackbar.make(view, "詳細画面へ", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                categoryList.remove(position);

//                itemListAdapter.remove(itemListAdapter.getItem(position));
//                itemListAdapter.notifyDataSetChanged();

                return false;
            }
        });

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //要素群の書き込み
//        saveItems();

    }

    //要素群の書き込み
    private void saveItems() {
        //ArrayListをJSONに変換
        String json = list2json(categoryList);

        //プリファレンスへの書き込み
        SharedPreferences pref = getActivity().getSharedPreferences(
                "Category", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("categoryList", json);
        editor.commit();
    }

    //要素群の読み込み
    private void loadItems() {
        //プリファレンスからの読み込み
        SharedPreferences pref = getActivity().getSharedPreferences(
                "Category", MODE_PRIVATE);
        String json = pref.getString("itemList","");

        //JSONをArrayListに変換
        categoryList = items2list(json);
    }

    //ArrayListをJSONに変換(5)
    private String list2json(ArrayList<Category> categories) {
        try {
            JSONArray array = new JSONArray();
            for (Category category : categories) {
                JSONObject obj = new JSONObject();
                obj.put("title", category.categoryName);
                obj.put("task", category.task);
                obj.put("icon", category.icon);
                array.put(obj);
            }
            return array.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    //JSONをArrayListに変換(6)
    private ArrayList<Category> items2list(String json) {
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Category category = new Category();
                category.categoryName = obj.getString("title");
                category.task = obj.getBoolean("task");
                category.icon = obj.getString("icon");
                categories.add(category);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categories;
    }

}