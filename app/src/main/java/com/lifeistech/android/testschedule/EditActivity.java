package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.lifeistech.android.testschedule.ItemClass.Item;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class EditActivity extends AppCompatActivity {

    private MaterialEditText editText, newCatEdit;
    private Spinner categorySpinner;
    private CheckBox checkBox;
    private Button okBtn, cancelBtn;

    private int position;
    private Boolean edit;

    // Realm
    Realm realm;
    RealmResults<Item> result;

    private ArrayList<String> categoryList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        // タイトル設定
        setTitle("新しいタスクを追加");

        // ひもづけ
        editText = (MaterialEditText) findViewById(R.id.itemName);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        newCatEdit = (MaterialEditText) findViewById(R.id.newCatEdit);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        okBtn = (Button) findViewById(R.id.okBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        // 新規作成ではなくItemの編集の時
        picItem();

        // スピナーの設定

        // Realm全体の初期化
        Realm.init(getApplicationContext());
        // このスレッドのためのRealmインスタンスを取得
        realm = Realm.getDefaultInstance();
        result = realm.where(Item.class).findAll();

        for (int i = 0; i < result.size(); i++) {
            if (categoryList.lastIndexOf(result.get(i)) == 0) {
                categoryList.add(result.get(i).getCategory());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int i = 0; i < categoryList.size(); i++) {
            adapter.add(categoryList.get(i));
        }
        categorySpinner.setAdapter(adapter);

        if (edit) {
            for (int i = 0; i < categoryList.size(); i++) {
                if (result.get(position).getCategory() == categoryList.get(i)) {
                    categorySpinner.setSelection(i);
                }
            }
        }

        // ボタンの設定
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().toString().matches("")) {
                    // 未入力 Error
                    Snackbar.make(v, "タスクを入力してください", Snackbar.LENGTH_SHORT).show();

                } else {

//                    realm.beginTransaction();

                    if (edit) {
                        // edit task

                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                final Item item = new Item();
                                // データセット
                                item.setKey(result.get(position).getKey());
                                item.setItemName(editText.getText().toString());
                                // カテゴリの設定
                                if (newCatEdit.getText().toString().matches("")) {
                                    item.setCategory(categorySpinner.getSelectedItem().toString());
                                } else {
                                    item.setCategory(newCatEdit.getText().toString());
                                }
                                item.setChecked(checkBox.isChecked());

                                // プライマリーキーが同じならアップデート
                                realm.copyToRealmOrUpdate(item);
                            }
                        });

                    } else {
                        // new task

                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                final Item item = new Item();
                                // データセット
                                item.setKey(UUID.randomUUID().toString());
                                item.setItemName(editText.getText().toString());
                                // カテゴリの設定
                                if (newCatEdit.getText().toString().matches("")) {
                                    item.setCategory(categorySpinner.getSelectedItem().toString());
                                } else {
                                    item.setCategory(newCatEdit.getText().toString());
                                }
                                item.setChecked(checkBox.isChecked());

                                // プライマリーキーが同じならアップデート
                                realm.copyToRealmOrUpdate(item);

                                Log.d("UUID", item.getKey());
                            }
                        });

                    }

                    realm.close();

                    finish();
                }

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void picItem() {

        // 編集の場合
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        edit = intent.getBooleanExtra("edit", false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Realmインスタンスを閉じます
        realm.close();
    }

}