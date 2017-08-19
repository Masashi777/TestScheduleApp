package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lifeistech.android.testschedule.Adapter.MakeFragmentPagerAdapter;
import com.lifeistech.android.testschedule.TestClass.Date;
import com.lifeistech.android.testschedule.TestClass.Test;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

public class MakeActivity extends BaseActivity {

    TextView textView;

    private MaterialEditText editText;
    private ViewPager viewPager;
    private Button addButton;

    private Test test;
    private List<Date> dateList;

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

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(addButtonCliclListener);

        //ViewPagerを使用したfragment
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(
                new MakeFragmentPagerAdapter(
                        getSupportFragmentManager()));
//        viewPager2 = (ViewPager) findViewById(R.id.viewPager2);
//        viewPager2.setAdapter(
//                new MakeFragmentPagerAdapter(
//                        getSupportFragmentManager()));

        for(int i = 0; i< 15; i++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.make_card, null);
                CardView cardView = (CardView) linearLayout.findViewById(R.id.cardView);
                TextView textBox = (TextView) linearLayout.findViewById(R.id.textBox);
                textBox.setText("CardView" + i);
                cardView.setTag(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, String.valueOf(v.getTag()) + "番目のCardViewがクリックされました", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
//                    Toast.makeText(MakeActivity.this, String.valueOf(v.getTag()) + "番目のCardViewがクリックされました", Toast.LENGTH_SHORT).show();
                }
            });
//            cardLinear.addView(linearLayout,i);

//            LayoutInflater fragment_inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//                Fragment fragment = (Fragment) inflater.inflate(R.id.fragment, null);

//            textView = (TextView) findViewById(R.id.textView);
//            final Typeface kf = Typeface.createFromAsset(getAssets(), "KFhimaji.otf");
//            textView.setTypeface(kf);
        }

        // Fragmentを作成します
        CardFragment fragment = new CardFragment();
        // Fragmentの追加や削除といった変更を行う際は、Transactionを利用します
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 新しく追加を行うのでaddを使用します
        // 他にも、メソッドにはreplace removeがあります
        // メソッドの1つ目の引数は対象のViewGroupのID、2つ目の引数は追加するfragment
        transaction.add(R.id.container, fragment);
        // 最後にcommitを使用することで変更を反映します
        transaction.commit();

//        transaction.add(R.id.container, CardFragment.createInstance("hoge", Color.RED));
//        transaction.add(R.id.container, CardFragment.createInstance("fuga", Color.BLUE));


        transaction.add(R.id.container, CardFragment.createInstance("fuga", Color.BLUE));


        // タイトル設定
        setTitle("新しいテストを追加");

        editText = (MaterialEditText) findViewById(R.id.TestEdit);
/**
 *NumberPicker等はMakeActivityに紐づくレイアウト(activity_make.xmlとその下にincludeされてるレイアウト)上にはありません！
 *CardFragmentのに紐づくレイアウト（fragment_card.xml）上にあるので、そっちで操作します。
 **/

    }

    View.OnClickListener addButtonCliclListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //ここでカードビューからテスト詳細を取得
            Fragment cardFragment = getSupportFragmentManager().findFragmentById(R.id.container);
            if (cardFragment != null && cardFragment instanceof CardFragment) {
                ((CardFragment) cardFragment).addData(); //CardFragmentのaddDaraメソッドにここでしたい処理を丸投げ
            }

            // TestNameを追加
            test.setTestName(editText.getText().toString());

            // Fragmentからのデータ取得
            Intent intent = getIntent();
            Date day1, day2, day3;
            day1 = (Date) intent.getSerializableExtra("day");
            dateList.set(1, day1);
            test.setDateList(dateList);

            // Activityをスタート
            Intent intent2 = new Intent(getApplicationContext(), ScheduleActivity.class);
            intent2.putExtra("Test", test);
            startActivity(intent2);

        }
    };
}
