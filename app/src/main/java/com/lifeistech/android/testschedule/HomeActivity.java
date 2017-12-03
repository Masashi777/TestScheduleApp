package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.stetho.Stetho;
import com.lifeistech.android.testschedule.Adapter_Fragment.HomeFragmentPagerAdapter;
import com.lifeistech.android.testschedule.ItemClass.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.lifeistech.android.testschedule.GsonConverter.loadCategories;

public class HomeActivity extends BaseActivity {
    // Layout
    private ViewPager viewPager;

    // UI
    private ArrayList<Item> itemList;//要素群

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                SharedPreferences.Editor catEditor = getApplicationContext().getSharedPreferences("categoryList", 0).edit();
//                catEditor.clear().commit();
//                SharedPreferences.Editor itemEditor = getSharedPreferences("itemList", 0).edit();
//                itemEditor.clear().commit();

                Snackbar.make(view, "All Clear SharedPreferences", Snackbar.LENGTH_SHORT).show();

//                if (loadCategories(getApplicationContext()).size() == 0) {
//                    // NO CATEGORIES
//                    Snackbar.make(view, "カテゴリを作成してください", Snackbar.LENGTH_SHORT).show();
//                } else {
//                    Intent intent = new Intent(getApplicationContext(), EditActivity.class);
//                    startActivity(intent);
//                }
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
        setTitle("タスク一覧！");

        // ひもづけ
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        //要素群の読み込み
        itemList = new ArrayList<Item>();
        loadItems();

        setViews();
    }

    private void setViews() {

        // PagerAdapterの作成
        // データの処理
        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(fragmentManager);
        try {
            // 新規作成で受け取った場合
            // from EditActivity (New Item)
            Intent intent = getIntent();
            adapter.putItem((Item) intent.getSerializableExtra("item"));

        } catch (Exception e) {
            // 受け取れなかった⇒編集
        }


        viewPager.setAdapter(adapter);

        // Tabレイアウトの設定
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    //アクティビティ停止時に呼ばれる
    @Override
    public void onStop() {
        super.onStop();

        //要素群の書き込み
        saveItems();
    }

    //要素群の書き込み
    private void saveItems() {
        //ArrayListをJSONに変換
        String json = list2json(itemList);

        //プリファレンスへの書き込み
        SharedPreferences pref = getSharedPreferences(
                "ToDoApp", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("itemList", json);
        editor.commit();
    }

    //要素群の読み込み
    private void loadItems() {
        //プリファレンスからの読み込み
        SharedPreferences pref = getSharedPreferences(
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
//                item.category = obj.getInt("category");
                item.checked = obj.getBoolean("checked");
                items.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

}