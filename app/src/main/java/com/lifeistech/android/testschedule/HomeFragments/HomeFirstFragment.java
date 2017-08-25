package com.lifeistech.android.testschedule.HomeFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.MPPointF;
import com.lifeistech.android.testschedule.BaseFragment;
import com.lifeistech.android.testschedule.R;

import java.util.ArrayList;

public class HomeFirstFragment extends BaseFragment {

    private PieChart pieChart;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_home_first, container, false);

        pieChart = (PieChart) view.findViewById(R.id.pieChart);
        listView = (ListView) view.findViewById(R.id.listView);


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


        // リストの設定
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1);

        for (int i = 0; i < 10; i++) {
            adapter.add("Item" + i);
        }

        listView.setAdapter(adapter);

        setData();

        return view;

    }

    private void setData() {

        // PieEntryを使ってentriesにデータをセット
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        entries.add(new PieEntry(65, "終わったタスク"));
        entries.add(new PieEntry(35, "残っているタスク"));

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
