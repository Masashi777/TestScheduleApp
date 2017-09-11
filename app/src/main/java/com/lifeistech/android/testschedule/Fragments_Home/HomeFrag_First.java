package com.lifeistech.android.testschedule.Fragments_Home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.MPPointF;
import com.lifeistech.android.testschedule.BaseFragment;
import com.lifeistech.android.testschedule.DetailActivity;
import com.lifeistech.android.testschedule.GsonConverter;
import com.lifeistech.android.testschedule.ItemClass.Category;
import com.lifeistech.android.testschedule.ItemClass.Item;
import com.lifeistech.android.testschedule.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;

public class HomeFrag_First extends BaseFragment {

    private PieChart pieChart;
    private TextView commentText;
    private Button detailBtn;

    private ArrayList<Category> categoryList;
    private ArrayList<Item> itemList;
    private String chartLabel[] = {"終わったタスク", "残ってるタスク"};
    private int chartAmount[] = new int[2];
    private int a, b;

    private String[] comments = {
            "グラフで優先してやるタスクを見つけよう！",
        "今日も元気に頑張ろう",
        "タスクはこまめに確認！",
        "気になったらとりあえずタスクしよう！",
        "やらなきゃいけないことは早めに！",
        "グラフで優先してやるタスクを見つけよう！"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(this).build());
    }

    private RealmConfiguration buildRealmConfiguration() {
        return new RealmConfiguration.Builder(this)
                .schemaVersion(1L)
                .migration(new RealmMigration() {
                    @Override
                    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
                        if (oldVersion == 0L) {
                            final RealmObjectSchema tweetSchema = realm.getSchema().get("Tweet");
                            tweetSchema.addField("favorited", boolean.class);
                            //noinspection UnusedAssignment
                            oldVersion++;
                        }
                    }
                })
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_home_first, container, false);

        pieChart = (PieChart) view.findViewById(R.id.pieChart);
        commentText = (TextView) view.findViewById(R.id.commentText);
        detailBtn = (Button) view.findViewById(R.id.detailBtn);

        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DetailActivity.class);
                startActivity(intent);
            }
        });

//<<<<<<< HEAD:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Home/HomeFrag_First.java
        // データの取得
        categoryList = GsonConverter.loadCategories(getContext());

        int finish = 0;
        int all = 0;
        for (int n = 0; n < categoryList.size(); n++) {
            for (int m = 0; m < categoryList.get(n).getItemList().size(); m++) {
                all++;
                if (categoryList.get(n).getItemList().get(m).isChecked()) {
                    finish++;
                }
            }
        }
        chartAmount[0] = finish;
        chartAmount[1] = all - finish;


//=======
//        commentText.setText(comments[(int) Math.random()*5]);
//>>>>>>> dev2:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Home/HomeFirstFragment.java

        // Chartの設定
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        // センターテキスト
        pieChart.setCenterTextTypeface(mTfLight);
        pieChart.setCenterText("残りのタスク");

        // 内側の円の設定
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(30f);

        // 内側の円周のライン
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        // センターテキストの有無
        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//        pieChart.setOnChartValueSelectedListener(this);


        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
//        mChart.spin(2000, 0, 360);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTypeface(YuGothM);
        pieChart.setEntryLabelTextSize(12f);


        // データを取得
//        Bundle bundle = getArguments();
//        itemList = (ArrayList<Item>) bundle.getSerializable("itemList");

        /**
         * データの振り分け作業
         */
        a = 5;
        b = 11;

        setData();

        return view;

    }

    private void setData() {

        // PieEntryを使ってentriesにデータをセット
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

//<<<<<<< HEAD:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Home/HomeFrag_First.java
        for (int i = 0; i < chartLabel.length; i++) {
            entries.add(new PieEntry(chartAmount[i], chartLabel[i]));
        }
//=======
//        entries.add(new PieEntry(14, "終わったタスク"));
//        entries.add(new PieEntry(11, "残っているタスク"));
//>>>>>>> dev2:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Home/HomeFirstFragment.java

        PieDataSet dataSet = new PieDataSet(entries, "");

        //アイコンの有無
        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors (カラーの設定)
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#E91E63"));
        colors.add(Color.parseColor("#448AFF"));

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(YuGothM);
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);

        pieChart.invalidate();
    }

}
