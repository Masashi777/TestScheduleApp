package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.lifeistech.android.testschedule.ItemClass.Category;
import com.lifeistech.android.testschedule.ItemClass.Item;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

import static com.lifeistech.android.testschedule.GsonConverter.loadCategories;
import static com.lifeistech.android.testschedule.GsonConverter.loadItems;
import static com.lifeistech.android.testschedule.GsonConverter.saveItems;

public class EditActivity extends BaseActivity {

    private MaterialEditText editText;
    private Spinner categorySpinner;
    private CheckBox checkBox;
    private Button okBtn, cancelBtn;

    private int position;
    private Boolean edit;

    private ArrayList<Category> categoryList = new ArrayList<Category>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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

        // LoadCategory
        categoryList = loadCategories(getApplicationContext());

        // スピナーの設定
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int i = 0; i < categoryList.size(); i++) {
            adapter.add(categoryList.get(i).getCategoryName());
        }
        categorySpinner.setAdapter(adapter);

<<<<<<< HEAD
=======

>>>>>>> e9f0f5c5c7b6ae191b0fdcd934bc55b06591b084
        // ボタンの設定
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = new Item();
                item.setItemName(editText.getText().toString());
                item.setCategory(categorySpinner.getSelectedItem().toString());
                item.setChecked(checkBox.isChecked());

                ArrayList<Item> itemList = new ArrayList<Item>();
                itemList = loadItems(getApplicationContext());
                if (edit) {
                    itemList.set(position, item);
                } else {
                    itemList.add(item);
                }
                saveItems(getApplicationContext(), itemList);

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
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

        // 編集の場合
        try {
            // データの編集
            Item item = new Item();
            Intent intent = getIntent();
            item = (Item) intent.getSerializableExtra("item");
            position = intent.getIntExtra("position", 0);

            editText.setText(item.getItemName());
//            categorySpinner.setSelection(item.getCategory().);
            checkBox.setChecked(item.isChecked());

            edit = true;
        } catch (Exception e) {
            // データを取得できなかったら新規作成
            edit = false;
        }

    }

}
