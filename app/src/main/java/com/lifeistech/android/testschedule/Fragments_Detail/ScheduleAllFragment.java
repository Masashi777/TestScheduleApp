package com.lifeistech.android.testschedule.Fragments_Detail;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.lifeistech.android.testschedule.BaseFragment;
import com.lifeistech.android.testschedule.GsonConverter;
import com.lifeistech.android.testschedule.ItemClass.Category;
import com.lifeistech.android.testschedule.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Masashi Hamaguchi on 2017/07/30.
 */

public class ScheduleAllFragment extends BaseFragment {

//    OnChartValueSelectedListener

    private PieChart mChart;

//    private Test test;
//    private List<Subject> subjectList;
//    private String testName;

    private ArrayList<Category> categoryList = new ArrayList<Category>();


    public static Fragment newInstance() {

        return new ScheduleAllFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Bundle bundle = getArguments();
//        categoryList = (ArrayList<Category>) bundle.getSerializable("categoryList");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_detail_all, null);

        // ひもづけ
        mChart = (PieChart) view.findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        // センターテキスト
        mChart.setCenterTextTypeface(mTfLight);
//        mChart.setCenterText(generateCenterSpannableText());

        // 内側の円の設定
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        // 内側の円周のライン
        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        // センターテキストの有無
        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//        mChart.setOnChartValueSelectedListener(this);


        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
//        mChart.spin(2000, 0, 360);


        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTypeface(YuGothM);
        mChart.setEntryLabelTextSize(12f);



        // データ処理
        /** Serializableで受け取るところでエラー発生 */

        // グラフにデータをセット
        setData();


        return view;
    }

    private void setData() {

        // データ処理
        ArrayList<Category> categoryList = new ArrayList<Category>();
        categoryList = GsonConverter.loadCategories(getContext());

//        for (int i = 0; i < categoryList.size(); i++) {
//            Category categoryList.get
//        }


        // PieEntryを使ってentriesにデータをセット
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

//        try {
//            for (int i = 0; i < subjectList.size(); i++) {
//                entries.add(new PieEntry(subjectList.get(i).getPriority(), subjectList.get(i).getSubjectName()));
//            }
//        } catch (Exception e) {
//
//        }


        PieDataSet dataSet = new PieDataSet(entries, "教科一覧");

        //アイコンの有無
        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors (カラーの設定)

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (String c : popColor)
            colors.add(Color.parseColor(c));

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(YuGothM);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

//    private SpannableString generateCenterSpannableText() {

//        SpannableString s = new SpannableString(testName + "\ndeveloped by Masashi Hamaguchi");
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
