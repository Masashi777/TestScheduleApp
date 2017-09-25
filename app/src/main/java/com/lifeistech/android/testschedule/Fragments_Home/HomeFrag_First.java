package com.lifeistech.android.testschedule.Fragments_Home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.lifeistech.android.testschedule.Adapter_ListView.ItemListAdapter;
import com.lifeistech.android.testschedule.BaseFragment;
import com.lifeistech.android.testschedule.EditActivity;
import com.lifeistech.android.testschedule.ItemClass.Item;
import com.lifeistech.android.testschedule.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class HomeFrag_First extends BaseFragment {

    private PieChart pieChart;
    private TextView commentText;
    private ListView itemListView;

    private ItemListAdapter itemListAdapter;
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private String chartLabel[] = {"勉強", "遊び", "ゲーム"};
    private int chartAmount[] = {10, 2, 5};

    private Realm realm;
    private RealmChangeListener realmListener;

    private String[] comments = {
            "グラフで優先してやるタスクを見つけよう！",
        "今日も元気に頑張ろう",
        "タスクはこまめに確認！",
        "気になったらとりあえずタスクしよう！",
        "やらなきゃいけないことは早めに！",
        "グラフで優先してやるタスクを見つけよう！"
    };

    private String[] items = {
            "日本史レポート",
            "文化祭打ち合わせい",
            "ポスター作成",
            "実験レポート",
            "練習試合",
            "動画撮影",
            "単語テスト"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(getContext());
        realm = Realm.getDefaultInstance();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {

                RealmResults<Item> result = realm.where(Item.class).findAll();

                for (int i = 0; i < result.size(); i++) {
                    itemList.add(result.get(i));
                }
                itemListAdapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_home_first, container, false);

        pieChart = (PieChart) view.findViewById(R.id.pieChart);
        commentText = (TextView) view.findViewById(R.id.commentText);
        itemListView = (ListView) view.findViewById(R.id.itemList);

        // リストへのボタンの配置
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), EditActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("edit", true);
                startActivity(intent);

//                if (view.getId() == R.id.checkBox) {
//                    Item item = itemList.get(position);
//                    if (item.isChecked()) {
//                        item.setChecked(false);
//                    } else {
//                        item.setChecked(true);
//                    }
//                    itemList.set(position, item);
//                    itemListAdapter.notifyDataSetChanged();
//
//                } else {
////                    saveItems(getContext(), itemList);
//                    Intent intent = new Intent(getContext(), EditActivity.class);
//                    intent.putExtra("item", itemList.get(position));
//                    intent.putExtra("position", position);
//                    startActivity(intent);
//
//                    Log.e("itemList", "selected");
//                }

            }
        });


        // ListViewへの表示
        RealmResults<Item> result = realm.where(Item.class).findAll();

//        for (int i = 0; i < result.size(); i++) {
//            itemList.add(result.get(i));
//        }

//        itemListAdapter = new ItemListAdapter(getContext(), result);
//        itemListView.setAdapter(itemListAdapter);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, items);
        itemListView.setAdapter(arrayAdapter);

        realm.close();



        // Chartの設定
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        // センターテキスト
//        pieChart.setCenterTextTypeface(mTfLight);
//        pieChart.setCenterText("残りのタスク");

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
        pieChart.setDrawCenterText(false);

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



        setData();

        return view;

    }

    private void setData() {

        // PieEntryを使ってentriesにデータをセット
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        for (int i = 0; i < chartLabel.length; i++) {
            entries.add(new PieEntry(chartAmount[i], chartLabel[i]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        // アイコンの有無
        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors (カラーの設定)
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#FA4C4C"));
        colors.add(Color.parseColor("#1985FF"));
        colors.add(Color.parseColor("#FAC804"));

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

    @Override
    public void onDestroy() {
        super.onDestroy();

        // リスナーを削除します
        realm.removeChangeListener(realmListener);
        // Realmインスタンスを閉じます
        realm.close();
    }

}
