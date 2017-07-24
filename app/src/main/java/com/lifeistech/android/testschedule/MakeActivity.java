package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.lifeistech.android.testschedule.CustomClass.MyFragmentStatePagerAdapter;
import com.lifeistech.android.testschedule.TestClass.Date;
import com.lifeistech.android.testschedule.TestClass.Subject;
import com.lifeistech.android.testschedule.TestClass.Test;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;
import java.util.List;

public class MakeActivity extends NavigationActivity {

    TextView textView;
    MaterialEditText editText, subject1, subject2, subject3, subject4;
    ViewPager viewPager;

    private NumberPicker monthPicker, dayPicker, priority1, priority2, priority3, priority4;

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


        //ViewPagerを使用したfragment
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(
                new MyFragmentStatePagerAdapter(
                        getSupportFragmentManager()));
//        viewPager2 = (ViewPager) findViewById(R.id.viewPager2);
//        viewPager2.setAdapter(
//                new MyFragmentStatePagerAdapter(
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

        transaction.add(R.id.container, CardFragment.createInstance("hoge", Color.RED));
        transaction.add(R.id.container, CardFragment.createInstance("fuga", Color.BLUE));


        // タイトル設定
        setTitle("新しいテストを追加");
        // NumberPickerの設定

        // NumberPicker のインスタンスを取得
        monthPicker = (NumberPicker) findViewById(R.id.monthPicker);
        dayPicker = (NumberPicker) findViewById(R.id.dayPicker);
        priority1 = (NumberPicker) findViewById(R.id.priority1);
        priority2 = (NumberPicker) findViewById(R.id.priority2);
        priority3 = (NumberPicker) findViewById(R.id.priority3);
        priority4 = (NumberPicker) findViewById(R.id.priority4);

        // 最大値、最小値を設定
        Calendar cal = Calendar.getInstance();

//        monthPicker.setMinValue(1);
//        monthPicker.setMaxValue(12);
//        monthPicker.setValue(cal.get(Calendar.MONTH));
//
//        dayPicker.setMinValue(1);
//        dayPicker.setMaxValue(31);
//        dayPicker.setValue(cal.get(Calendar.DAY_OF_MONTH));
//
//        priority1.setMinValue(1);
//        priority1.setMaxValue(5);
//        priority2.setMinValue(1);
//        priority2.setMaxValue(5);
//        priority3.setMinValue(1);
//        priority3.setMaxValue(5);
//        priority4.setMinValue(1);
//        priority4.setMaxValue(5);
//
//        priority1.setValue(1);
//        priority2.setValue(1);
//        priority3.setValue(1);
//        priority4.setValue(1);


        //EditTextの設定
        editText = (MaterialEditText) findViewById(R.id.TestEdit);
        subject1 = (MaterialEditText) findViewById(R.id.subject1);
        subject2 = (MaterialEditText) findViewById(R.id.subject2);
        subject3 = (MaterialEditText) findViewById(R.id.subject3);
        subject4 = (MaterialEditText) findViewById(R.id.subject4);


    }

    public void addTest(View v) {

        //ここでカードビューからテスト詳細を取得

        //TestClass
        Test test = new Test();
        test.setTestName(editText.getText().toString());

        //日付の設定
        Date day1 = new Date();
//        day1.setMonth(monthPicker.getValue());
//        day1.setDay(dayPicker.getValue());

        List<Date> dateList = test.getDateList();
//        dateList.add(day1);

        //Subjectの設定
        Subject subjectA = new Subject();
        Subject subjectB = new Subject();
        Subject subjectC = new Subject();
        Subject subjectD = new Subject();

//        subjectA.setSubjectName(subject1.getText().toString());
//        subjectB.setSubjectName(subject2.getText().toString());
//        subjectC.setSubjectName(subject3.getText().toString());
//        subjectD.setSubjectName(subject4.getText().toString());

//        subjectA.setPriority(priority1.getValue());
//        subjectB.setPriority(priority2.getValue());
//        subjectC.setPriority(priority3.getValue());
//        subjectD.setPriority(priority4.getValue());

        List<Subject> subjectList = day1.getSubjectList();
//        subjectList.add(subjectA);
//        subjectList.add(subjectB);
//        subjectList.add(subjectC);
//        subjectList.add(subjectD);

        //TestClassへ追加
        day1.setSubjectList(subjectList);
        test.setDateList(dateList);

        //Intentの生成
        Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
        intent.putExtra("test", test);
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);

    }
}
