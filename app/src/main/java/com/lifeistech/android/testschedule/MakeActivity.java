package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lifeistech.android.testschedule.CustomClass.MyFragmentStatePagerAdapter;
import com.lifeistech.android.testschedule.TestClass.Test;

public class MakeActivity extends AppCompatActivity {

    TextView textView;

    ViewPager viewPager1, viewPager2;

    Test test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
        LinearLayout cardLinear = (LinearLayout)this.findViewById(R.id.cardLinear);
        cardLinear.removeAllViews();

        //ViewPagerを使用したfragment
        viewPager1 = (ViewPager) findViewById(R.id.viewPager1);
        viewPager1.setAdapter(
                new MyFragmentStatePagerAdapter(
                        getSupportFragmentManager()));
        viewPager2 = (ViewPager) findViewById(R.id.viewPager2);
        viewPager2.setAdapter(
                new MyFragmentStatePagerAdapter(
                        getSupportFragmentManager()));

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
            cardLinear.addView(linearLayout,i);

//            LayoutInflater fragment_inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//                Fragment fragment = (Fragment) inflater.inflate(R.id.fragment, null);

            textView = (TextView) findViewById(R.id.textView);
            final Typeface kf = Typeface.createFromAsset(getAssets(), "KFhimaji.otf");
            textView.setTypeface(kf);
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
    }

    public void addTest(View v) {

        //ここでカードビューからテスト詳細を取得

        Intent intent = new Intent(this, ScheduleActivity.class);
//        intent.putExtra("test", test);
        startActivity(intent);

    }
}
