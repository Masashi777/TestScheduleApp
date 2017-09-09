package com.lifeistech.android.testschedule.Fragments_Detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.MPPointF;
import com.lifeistech.android.testschedule.BaseFragment;
import com.lifeistech.android.testschedule.GsonConverter;
import com.lifeistech.android.testschedule.ItemClass.Category;
import com.lifeistech.android.testschedule.R;

import java.util.ArrayList;

/**
 * Created by Masashi Hamaguchi on 2017/07/30.
 */

public class DetailFrag_All extends BaseFragment {

//<<<<<<< HEAD:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Detail/DetailFrag_All.java
    private PieChart pieChart;
//=======
//    private PieChart mChart;
//>>>>>>> dev2:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Detail/ScheduleAllFragment.java

    private ArrayList<Category> categoryList = new ArrayList<Category>();
    private String catList[] = new String[categoryList.size()];
    private int amountList[] = new int[categoryList.size()];
    private int colorList[] = new int[categoryList.size()];

    public static Fragment newInstance() {

        return new DetailFrag_All();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryList = GsonConverter.loadCategories(getContext());

        for (int i = 0; i < categoryList.size(); i++) {
            catList[i] = categoryList.get(i).getCategoryName();
            amountList[i] = categoryList.get(i).getItemList().size();
            colorList[i] = categoryList.get(i).getColor();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_detail_all, null);

        // ひもづけ
        pieChart = (PieChart) view.findViewById(R.id.chart1);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        // センターテキスト
//<<<<<<< HEAD:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Detail/DetailFrag_All.java
        pieChart.setCenterTextTypeface(mTfLight);
//        pieChart.setCenterText(generateCenterSpannableText());

        // 内側の円の設定
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
//=======
//        mChart.setCenterTextTypeface(mTfLight);
//        mChart.setCenterText("カテゴリ別表示");
//        // generateCenterSpannableText()
//
//        // 内側の円の設定
//        mChart.setDrawHoleEnabled(true);
//        mChart.setHoleColor(R.color.cyanLightPrimary);
//        mChart.setCenterTextColor(Color.WHITE);
//>>>>>>> dev2:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Detail/ScheduleAllFragment.java

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

        // pieChart.setUnit(" €");
        // pieChart.setDrawUnitsInChart(true);

        // add a selection listener
//        pieChart.setOnChartValueSelectedListener(this);


        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
//        pieChart.spin(2000, 0, 360);


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



        // データ処理
        /** Serializableで受け取るところでエラー発生 */

        // グラフにデータをセット
        setData();


        return view;
    }

    private void setData() {

//<<<<<<< HEAD:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Detail/DetailFrag_All.java
        // PieEntryを使ってentriesにデータをセット
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        for (int i = 0; i < catList.length; i++) {
            entries.add(new PieEntry(amountList[i], catList[i]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "カテゴリ一覧");
//=======
//        // データ処理
////        ArrayList<Category> categoryList = new ArrayList<Category>();
////        categoryList = GsonConverter.loadCategories(getContext());
//
////        for (int i = 0; i < categoryList.size(); i++) {
////            Category categoryList.get
////        }
//
//
//        // PieEntryを使ってentriesにデータをセット
//        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
//
//        entries.add(new PieEntry(5, "勉強"));
//        entries.add(new PieEntry(7, "文化祭準備"));
//        entries.add(new PieEntry(3, "遊び"));
//        entries.add(new PieEntry(8, "買い物"));
//        entries.add(new PieEntry(2, "家でやること"));
//
////        try {
////            for (int i = 0; i < subjectList.size(); i++) {
////                entries.add(new PieEntry(subjectList.get(i).getPriority(), subjectList.get(i).getSubjectName()));
////            }
////        } catch (Exception e) {
////
////        }
//
//
//        PieDataSet dataSet = new PieDataSet(entries, "");
//>>>>>>> dev2:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Detail/ScheduleAllFragment.java

        //アイコンの有無
        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors (カラーの設定)

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // colorListから追加
        for (int i = 0; i < colorList.length; i++) {
            colors.add(colorList[i]);
        }

//        for (String c : popColor)
//            colors.add(Color.parseColor(c));
//
//        colors.add(ColorTemplate.getHoloBlue());

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

//    private SpannableString generateCenterSpannableText() {
//
//        SpannableString s = new SpannableString();
//        s.setSpan(new RelativeSizeSpan(1.7f), 0, 5, 0);
//        s.setSpan(new StyleSpan(Typeface.NORMAL), 5, s.length() - 15, 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 5, s.length() - 15, 0);
//        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 20, 0);
//        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 19, s.length(), 0);
//        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 19, s.length(), 0);
//        return s;
//    }

//    @Override
//    public void onValueSelected(Entry e, Highlight h) {
//
//        if (e == null)
//            return;
//        Log.i("VAL SELECTED",
//                "Value: " + e.getY() + ", index: " + h.getX()
//                        + ", DataSet index: " + h.getDataSetIndex());
//    }

//    @Override
//    public void onNothingSelected() {
//        Log.i("PieChart", "nothing selected");
//    }

}
