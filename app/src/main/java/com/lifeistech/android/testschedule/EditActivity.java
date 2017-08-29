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

public class EditActivity extends BaseActivity {

    private MaterialEditText editText;
    private Spinner categorySpinner;
    private CheckBox checkBox;
    private Button okBtn, cancelBtn;

    private int position;
    private Boolean edit;
    private String catName;
    private int catPosition;
    private int itemPosition;

    private ArrayList<Category> categoryList = new ArrayList<Category>();

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

        // LoadCategory
        categoryList = loadCategories(getApplicationContext());

        // 新規作成ではなくItemの編集の時
        picItem();



        // スピナーの設定
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int i = 0; i < categoryList.size(); i++) {
            adapter.add(categoryList.get(i).getCategoryName());
        }

        categorySpinner.setAdapter(adapter);

        if (edit) {
            categorySpinner.setSelection(catPosition);
        }


        // ボタンの設定
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().toString().matches("")) {
                    // 未入力 Error
                    Snackbar.make(v, "タスクを入力してください", Snackbar.LENGTH_SHORT).show();

                } else {
                    Item item = new Item();
                    item.setItemName(editText.getText().toString());
                    item.setChecked(checkBox.isChecked());

                    int spinnerPosition = categorySpinner.getSelectedItemPosition();

                    // switch
                    ArrayList<Item> itemList = new ArrayList<Item>();
                    Category category = new Category();

                    if (edit) {
                        // edit task
                        if (catPosition == spinnerPosition) {
                            // same category -case 1
                            category = categoryList.get(catPosition);
                            itemList = category.getItemList();
                            itemList.set(itemPosition, item);
                            category.setItemList(itemList);
                            categoryList.set(catPosition, category);

                        } else {
                            // different category -case 2

                            // Delete Item
                            categoryList.get(catPosition).getItemList().remove(itemPosition);

                            // Add Item
                            category = categoryList.get(spinnerPosition);
                            itemList = category.getItemList();
                            itemList.add(item);
                            category.setItemList(itemList);
                            categoryList.set(spinnerPosition, category);

                        }

                    } else {
                        // new task -case 3
                        category = categoryList.get(spinnerPosition);
                        itemList = category.getItemList();
                        itemList.add(item);
                        category.setItemList(itemList);
                        categoryList.set(spinnerPosition, category);

                    }


                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }

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
//            position = intent.getIntExtra("position", 0);

            editText.setText(item.getItemName());
            checkBox.setChecked(item.isChecked());

            // カテゴリ検索
            for (int n = 0; n < categoryList.size(); n++) {
                for (int m = 0; m < categoryList.get(n).getItemList().size(); m++) {
                    String name = categoryList.get(n).getItemList().get(m).getItemName();
                    if (item.getItemName() == name) {
                        catName = categoryList.get(n).getCategoryName();
                        catPosition = n;
                        itemPosition = m;

                        break;
                    }
                }
            }

            edit = true;
        } catch (Exception e) {
            // データを取得できなかったら新規作成
            edit = false;
        }

    }

}
