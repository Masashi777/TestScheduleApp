package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lifeistech.android.testschedule.FragmentAdapter.MakeFragmentPagerAdapter;
import com.lifeistech.android.testschedule.MPAndroid.Example;
import com.lifeistech.android.testschedule.TestClass.Date;
import com.lifeistech.android.testschedule.TestClass.Test;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class MakeActivity extends BaseActivity implements CardFragment.SendListener {

    TextView textView;

    private MaterialEditText editText;
    private ViewPager viewPager;
    private Button addButton;

    private Test test = new Test();
    private List<Date> dateList = new ArrayList<Date>();
    private List<Test> testList = new ArrayList<Test>();

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);

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
        setTitle("新しいテストを追加");

        // ひもづけ
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        editText = (MaterialEditText) findViewById(R.id.TestEdit);

        // ボタンの設定
        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(addButtonClickListener);

        // PagerAdapterの作成
        String[] datas = new String[4];
        FragmentManager fragmentManager = getSupportFragmentManager();
        MakeFragmentPagerAdapter adapter = new MakeFragmentPagerAdapter(fragmentManager);

        viewPager.setAdapter(adapter);


/**
 *NumberPicker等はMakeActivityに紐づくレイアウト(activity_make.xmlとその下にincludeされてるレイアウト)上にはありません！
 *CardFragmentのに紐づくレイアウト（fragment_card.xml）上にあるので、そっちで操作します。
 **/

    }

    View.OnClickListener addButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //ここでカードビューからテスト詳細を取得
            for (int i = 0; i < 3; i++) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.container, new CardFragment(), String.valueOf(i));

                Fragment cardFragment = getSupportFragmentManager().findFragmentById(R.id.container);
                if (cardFragment != null && cardFragment instanceof CardFragment) {
                    ((CardFragment) cardFragment).addDate();
                }
            }

            Log.e("ButtonOnClickListener", "Tapped");

            /**
             * Fragment上のaddDateを実行
             * データをActivityに送信
             * 終了したら、インターフェイスを呼び出しActivityのpicDateを実行↓
             * ScheduleActivityへTestClassを渡す。
             */


            /**
             * testはオブジェクトなのでintentで渡すことはできません！プリミティブな変数しか渡せないよ！
             * 今回はそのままsetした内容をgetして送りました。
             * */

            // データを復活
            try {
                pref = getSharedPreferences("SaveTestList", getApplicationContext().MODE_PRIVATE);
                Gson gson = new Gson();
                testList = gson.fromJson(pref.getString("testGson", ""), new TypeToken<List<Test>>(){}.getType());

            } catch (Exception e) {

            }

            Example example = new Example();
            Test exam = new Test();
            exam = example.getExam1();
            testList.add(testList.size() + 1, exam);

            // 保存するとき
            // GsonでUserをJSON文字列に変換して保存する

            Gson gson = new Gson();
            gson.toJson(testList);
            pref.edit().putString("SaveTestList", gson.toJson(testList)).commit();

        }
    };

    // interface内のメソッドを実装します。
    @Override
    public void picDate(int tag, Date date) {

        // Fragmentからのデータ取得
        dateList.set(tag, date);

        nextActivity();

        Log.e("picDate", String.valueOf(tag));
    }

    public void nextActivity() {

        // TestNameを追加
        test.setTestName(editText.getText().toString());

        test.setDateList(dateList);

        // Activityをスタート
        Intent intent2 = new Intent(getApplicationContext(), ScheduleActivity.class);
        intent2.putExtra("Test", test);
        startActivity(intent2);

    }
}
