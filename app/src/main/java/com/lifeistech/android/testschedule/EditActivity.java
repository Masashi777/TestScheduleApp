package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lifeistech.android.testschedule.ItemClass.Item;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends BaseActivity {

    private MaterialEditText editText;
    private Spinner categorySpinner;
    private CheckBox checkBox;
    private Button okBtn, cancelBtn;

    private Item item;

    private String[] categoryList = {"遊び", "勉強", "マンガを読む", "部活", "趣味", "テスト勉強"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // タイトル設定
        setTitle("新しいタスクを追加");

        // ひもづけ
        editText = (MaterialEditText) findViewById(R.id.itemName);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        okBtn = (Button) findViewById(R.id.okBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        // 新規作成ではなくItemの編集の時
        picItem();

        // スピナーの設定
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int i = 0; i < categoryList.length; i++) {
            adapter.add(categoryList[i]);
        }
        categorySpinner.setAdapter(adapter);

        categorySpinner.set

        // ボタンの設定
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = new Item();
                item.setItemName(editText.getText().toString());
                item.setCategory(categorySpinner.getSelectedItemPosition());
//                item.setChecked(checkBox.isChecked());

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("item", item);
                startActivity(intent);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


/**
 *NumberPicker等はMakeActivityに紐づくレイアウト(activity_edit.xmlとその下にincludeされてるレイアウト)上にはありません！
 *CardFragmentのに紐づくレイアウト（fragment_card.xml）上にあるので、そっちで操作します。
 **/

    }

    private void picItem() {

        try {
            // データの編集
            Intent intent = getIntent();
            item = (Item) intent.getSerializableExtra("item");

            editText.setText(item.getItemName());
            categorySpinner.setSelection(item.getCategory());
            checkBox.setChecked(item.isChecked());
        } catch (Exception e) {
            // データを取得できなかったら新規作成
        }

    }


    // interface内のメソッドを実装します。
//    @Override
//    public void picDate(int tag, Date date) {
//
//        // Fragmentからのデータ取得
//        dateList.set(tag, date);
//
//        nextActivity();
//
//        Log.e("picDate", String.valueOf(tag));
//    }

//    public void nextActivity() {
//
//        // TestNameを追加
//        test.setTestName(editText.getText().toString());
//
//        test.setDateList(dateList);
//
//        // Activityをスタート
//        Intent intent2 = new Intent(getApplicationContext(), DetailActivity.class);
//        intent2.putExtra("Test", test);
//        startActivity(intent2);
//
//    }


}
