package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.lifeistech.android.testschedule.ItemClass.Item;
import com.rengwuxian.materialedittext.MaterialEditText;

import io.realm.Realm;
import io.realm.RealmResults;

public class CategoryEditActivity extends AppCompatActivity {

    private MaterialEditText editText;
    private Button okBtn, cancelBtn;

    private Boolean edit;
    private String category;

    // Realm
    private Realm realm;
    private RealmResults<Item> result;

//    private int red = R.color.red;
//    private int pink = R.color.pink;
//    private int indigo = R.color.indigo;
//    private int blue = R.color.blue;
//    private int teal = R.color.teal;
//    private int green = R.color.green;
//    private int deeporange = R.color.deeporange;
//    private int brown = R.color.brown;
//    private int bluegrey = R.color.bluegrey;

//    private RadioButton redBtn, pinkBtn, indigoBtn, blueBtn, tealBtn, greenBtn, deeporangeBtn, brownBtn, bluegreyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryedit);

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
        setTitle("カテゴリ編集");

        // ひもづけ
        editText = (MaterialEditText) findViewById(R.id.itemName);
        okBtn = (Button) findViewById(R.id.okBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

//        redBtn = (RadioButton) findViewById(R.id.red);
//        pinkBtn = (RadioButton) findViewById(R.id.pink);
//        indigoBtn = (RadioButton) findViewById(R.id.indigo);
//        blueBtn = (RadioButton) findViewById(R.id. blue);
//        tealBtn = (RadioButton) findViewById(R.id.teal);
//        greenBtn = (RadioButton) findViewById(R.id.green);
//        deeporangeBtn = (RadioButton) findViewById(R.id.deeprange);
//        brownBtn = (RadioButton) findViewById(R.id.brown);
//        bluegreyBtn = (RadioButton) findViewById(R.id.bluegrey);


        // 新規作成ではなくItemの編集の時
        picCategory();


        // ボタンの設定
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().toString().matches("")) {
                    // Error
                    Snackbar.make(v, "カテゴリを入力してください", Snackbar.LENGTH_SHORT).show();

                } else {
                    // Change CategoryName

                    // カテゴリを一括で変える
                    realm = Realm.getInstance(Realm.getDefaultConfiguration());
                    realm.beginTransaction();
                    result = realm.where(Item.class)
                            .equalTo("category", category)
                            .findAll();

                    for (Item item : result) {
                        item.setCategory(editText.getText().toString());
                        realm.copyToRealmOrUpdate(item);
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

    private void picCategory() {

        // 編集の場合
        try {
            // データの編集
            Intent intent = getIntent();
            category = intent.getStringExtra("category");

            edit = true;

        } catch (Exception e) {
            // データを取得できなかったら新規作成
            edit = false;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Realmインスタンスを閉じます
        realm.close();
    }

}