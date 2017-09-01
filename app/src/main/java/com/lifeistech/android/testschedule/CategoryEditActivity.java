package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.lifeistech.android.testschedule.ItemClass.Category;
import com.lifeistech.android.testschedule.Layout.TableRadioGroup;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

import static com.lifeistech.android.testschedule.GsonConverter.loadCategories;
import static com.lifeistech.android.testschedule.GsonConverter.saveCategories;

public class CategoryEditActivity extends BaseActivity {

    private MaterialEditText editText;
    private CheckBox checkBox;
    private TableRadioGroup tableRadioGroup;
    private Button okBtn, cancelBtn;

    private int position;
    private Boolean edit;

    private String[] categoryList = {"遊び", "勉強", "マンガを読む", "部活", "趣味", "テスト勉強"};

    private int red = R.color.red;
    private int pink = R.color.pink;
    private int indigo = R.color.indigo;
    private int blue = R.color.blue;
    private int teal = R.color.teal;
    private int green = R.color.green;
    private int deeporange = R.color.deeporange;
    private int brown = R.color.brown;
    private int bluegrey = R.color.bluegrey;

    private RadioButton redBtn, pinkBtn, indigoBtn, blueBtn, tealBtn, greenBtn, deeporangeBtn, brownBtn, bluegreyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryedit);

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
        setTitle("カテゴリ");

        // ひもづけ
        editText = (MaterialEditText) findViewById(R.id.itemName);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        tableRadioGroup = (TableRadioGroup) findViewById(R.id.radioGroup);
        okBtn = (Button) findViewById(R.id.okBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        redBtn = (RadioButton) findViewById(R.id.red);
        pinkBtn = (RadioButton) findViewById(R.id.pink);
        indigoBtn = (RadioButton) findViewById(R.id.indigo);
        blueBtn = (RadioButton) findViewById(R.id. blue);
        tealBtn = (RadioButton) findViewById(R.id.teal);
        greenBtn = (RadioButton) findViewById(R.id.green);
        deeporangeBtn = (RadioButton) findViewById(R.id.deeprange);
        brownBtn = (RadioButton) findViewById(R.id.brown);
        bluegreyBtn = (RadioButton) findViewById(R.id.bluegrey);


        // 新規作成ではなくItemの編集の時
        picCategory();


        // ボタンの設定
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (redBtn.isChecked() || pinkBtn.isChecked() || indigoBtn.isChecked() ||
                        blueBtn.isChecked() || tealBtn.isChecked() || greenBtn.isChecked() ||
                        deeporangeBtn.isChecked() || brownBtn.isChecked() || bluegreyBtn.isChecked()) {

                    Category category = new Category();
                    category.setCategoryName(editText.getText().toString());
                    category.setTask(checkBox.isChecked());

                    int id = tableRadioGroup.getCheckedRadioButtonId();
                    if (id == R.id.red) {
                        category.setColor(R.color.red);
                    } else if (id == R.id.pink) {
                        category.setColor(pink);
                    } else if (id == R.id.indigo) {
                        category.setColor(indigo);
                    } else if (id == R.id.blue) {
                        category.setColor(blue);
                    } else if (id == R.id.teal) {
                        category.setColor(teal);
                    } else if (id == R.id.green) {
                        category.setColor(green);
                    } else if (id == R.id.deeprange) {
                        category.setColor(deeporange);
                    } else if (id == R.id.brown) {
                        category.setColor(brown);
                    } else if (id == R.id.bluegrey) {
                        category.setColor(bluegrey);
                    }

                    ArrayList<Category> categoryList = new ArrayList<Category>();
                    categoryList = loadCategories(getApplicationContext());
                    if (edit) {
                        categoryList.set(position, category);
                    } else {
                        categoryList.add(category);
                    }
                    saveCategories(getApplicationContext(), categoryList);

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);

                } else {

                    ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
                    Snackbar.make(constraintLayout, "色を選択してください", Snackbar.LENGTH_SHORT).show();
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

    private void picCategory() {

        // 編集の場合
        try {
            // データの編集
            Category category = new Category();
            Intent intent = getIntent();
            category = (Category) intent.getSerializableExtra("category");
            position = intent.getIntExtra("position", 0);

            editText.setText(category.getCategoryName());
            checkBox.setChecked(category.isTask());

            int id = category.getColor();

            if (id == red) {
                redBtn.setChecked(true);
            } else if (id == pink) {
                pinkBtn.setChecked(true);
            } else if (id == indigo) {
                indigoBtn.setChecked(true);
            } else if (id == R.id.blue) {
                blueBtn.setChecked(true);
            } else if (id == R.id.teal) {
                tealBtn.setChecked(true);
            } else if (id == R.id.green) {
                greenBtn.setChecked(true);
            } else if (id == R.id.deeprange) {
                deeporangeBtn.setChecked(true);
            } else if (id == R.id.brown) {
                brownBtn.setChecked(true);
            } else if (id == R.id.bluegrey) {
                bluegreyBtn.setChecked(true);
            }


            edit = true;
        } catch (Exception e) {
            // データを取得できなかったら新規作成
            edit = false;
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
