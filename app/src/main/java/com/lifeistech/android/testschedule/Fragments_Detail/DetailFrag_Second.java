package com.lifeistech.android.testschedule.Fragments_Detail;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
//<<<<<<< HEAD:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Detail/DetailFrag_Second.java
//=======
//import com.github.mikephil.charting.formatter.IAxisValueFormatter;
//import com.github.mikephil.charting.formatter.LargeValueFormatter;
//import com.github.mikephil.charting.formatter.PercentFormatter;
//>>>>>>> dev2:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Detail/SecondFragment.java
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.lifeistech.android.testschedule.BaseFragment;
//<<<<<<< HEAD:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Detail/DetailFrag_Second.java
import com.lifeistech.android.testschedule.GsonConverter;
import com.lifeistech.android.testschedule.ItemClass.Category;
import com.lifeistech.android.testschedule.MPAndroid.MyValueFormatter;
import com.lifeistech.android.testschedule.R;

import java.util.ArrayList;
//=======
//import com.lifeistech.android.testschedule.MPAndroid.MyValueFormatter;
//import com.lifeistech.android.testschedule.R;
//
//import java.util.ArrayList;
//
//import io.realm.Realm;
//>>>>>>> dev2:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Detail/SecondFragment.java

/**
 * Created by Masashi Hamaguchi on 2017/07/26.
 */

public class DetailFrag_Second extends BaseFragment implements OnChartValueSelectedListener {

    private HorizontalBarChart horizontalBarChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;

//<<<<<<< HEAD:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Detail/DetailFrag_Second.java
    private ArrayList<Category> categoryList = new ArrayList<Category>();
//=======
//    protected Realm mRealm;
//>>>>>>> dev2:app/src/main/java/com/lifeistech/android/testschedule/Fragments_Detail/SecondFragment.java

//    private Example example = new Example();

//    private Test test;
//    private List<Subject> subjectList;
    private String testName;

    protected String[] xLavels = new String[] {
            "勉強", "文化祭準備", "遊び", "買い物", "家でやること"
    };

    public static Fragment newInstance() {
        return new DetailFrag_Second();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryList = GsonConverter.loadCategories(getContext());
        String catList[] = new String[categoryList.size()];
        int amountList[] = new int[categoryList.size()];

        for (int i = 0; i < categoryList.size(); i++) {
            catList[i] = categoryList.get(i).getCategoryName();
            amountList[i] = categoryList.get(i).getItemList().size();
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_detail_second, container, false);

//        tvX = (TextView) view.findViewById(R.id.tvXMax);
//        tvY = (TextView) view.findViewById(R.id.tvYMax);

//        mSeekBarX = (SeekBar) view.findViewById(R.id.seekBar1);
//        mSeekBarY = (SeekBar) view.findViewById(R.id.seekBar2);

        horizontalBarChart = (HorizontalBarChart) view.findViewById(R.id.horizontalBarChart);
        horizontalBarChart.setOnChartValueSelectedListener(this);
        // mChart.setHighlightEnabled(false);

        horizontalBarChart.setDrawBarShadow(false);

        horizontalBarChart.setDrawValueAboveBar(true);

        horizontalBarChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        horizontalBarChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        horizontalBarChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        horizontalBarChart.setDrawGridBackground(false);

        XAxis xl = horizontalBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setTypeface(mTfLight);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGranularity(0.1f);
        xl.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLavels[(int) value % xLavels.length];
            }
        });

        YAxis yl = horizontalBarChart.getAxisLeft();
        yl.setTypeface(mTfLight);
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(false);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        yl.setMaxWidth(100);
        yl.setAxisMaxValue(100);
//        yl.setInverted(true);

        YAxis yr = horizontalBarChart.getAxisRight();
        yr.setTypeface(mTfLight);
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        yr.setAxisMaxValue(100);
        yr.setMaxWidth(100);
//        yr.setInverted(true);



        setData();
        horizontalBarChart.setFitBars(true);
        horizontalBarChart.animateY(1000);

        // setting data
//        mSeekBarY.setProgress(50);
//        mSeekBarX.setProgress(12);

//        mSeekBarY.setOnSeekBarChangeListener(this);
//        mSeekBarX.setOnSeekBarChangeListener(this);

        Legend l = horizontalBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
        l.setTextSize(14);


        // データ処理
        /** Serializableで受け取るところでエラー発生 */

        // データセット
//        setData();


        return view;
    }


    private void setData() {

        float spaceForBar = 1f;

//        tvX.setText("" + (mSeekBarX.getProgress() + 1));
//        tvY.setText("" + (mSeekBarY.getProgress()));

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

//        for (int i = 0; i < 10; i++) {
//            float mult = (10 + 1);
//            float val1 = (float) (Math.random() * mult) + mult / 3;
//            float val2 = (float) (Math.random() * mult) + mult / 3;
//            float val3 = (float) (Math.random() * mult) + mult / 3;
//
//            yVals1.add(new BarEntry(
//                    i,
//                    new float[]{val1, val2, val3},
//                    getResources().getDrawable(R.drawable.star)));
//        }
        yVals1.add(new BarEntry(0 * spaceForBar, new float[]{40, 60}));
        yVals1.add(new BarEntry(1 * spaceForBar, new float[]{43, 57}));
        yVals1.add(new BarEntry(2 * spaceForBar, new float[]{67, 33}));
        yVals1.add(new BarEntry(3 * spaceForBar, new float[]{75, 25}));
        yVals1.add(new BarEntry(4 * spaceForBar, new float[]{50, 50}));


        BarDataSet set1; // = new BarDataSet(yVals1, "");

        if (horizontalBarChart.getData() != null &&
                horizontalBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) horizontalBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            horizontalBarChart.getData().notifyDataChanged();
            horizontalBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "");
            set1.setDrawIcons(false);
            set1.setColors(new int[]{Color.parseColor("#E91E63"), Color.parseColor("#448AFF")});
            set1.setStackLabels(new String[]{"終わったタスク", "終わってないタスク"});

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(set1);
            data.setValueFormatter(new MyValueFormatter());
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(16);
            data.setDrawValues(true);

            horizontalBarChart.setData(data);
        }

        horizontalBarChart.setFitBars(true);
        horizontalBarChart.invalidate();

//        float barWidth = 9f;
//        float spaceForBar = 10f;
//        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
//
//        yVals1.add(new BarEntry(0 * spaceForBar, 40, "勉強"));
//        yVals1.add(new BarEntry(1 * spaceForBar, 43, "文化祭準備"));
//        yVals1.add(new BarEntry(2 * spaceForBar, 67, "遊び"));
//        yVals1.add(new BarEntry(3 * spaceForBar, 75, "買い物"));
//        yVals1.add(new BarEntry(4 * spaceForBar, 50, "家でやること"));
//
//
//        //
//        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
//
//        yVals2.add(new BarEntry(0 * spaceForBar, 60));
//        yVals2.add(new BarEntry(1 * spaceForBar, 57));
//        yVals2.add(new BarEntry(2 * spaceForBar, 33));
//        yVals2.add(new BarEntry(3 * spaceForBar, 25));
//        yVals2.add(new BarEntry(4 * spaceForBar, 50));
//
////        for (int i = 0; i < count; i++) {
////            float val = (float) (Math.random() * range);
////            yVals1.add(new BarEntry(i * spaceForBar, val,
////                    getResources().getDrawable(R.drawable.star)));
////        }
//
//        BarDataSet set1;
//
//        if (horizontalBarChart.getData() != null &&
//                horizontalBarChart.getData().getDataSetCount() > 0) {
//            set1 = (BarDataSet)horizontalBarChart.getData().getDataSetByIndex(0);
////            set2 = (BarDataSet)horizontalBarChart.getData().getDataSetByIndex(1);
//            set1.setValues(yVals1);
//            set1.setValues(yVals2);
//            horizontalBarChart.getData().notifyDataChanged();
//            horizontalBarChart.notifyDataSetChanged();
//        } else {
//            set1 = new BarDataSet(yVals1, "DataSet 1");
////            set1 = new BarDataSet(yVals2, "DataSet 2");
//
//            set1.setDrawIcons(false);
////            set2.setDrawIcons(false);
//
//            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
//            dataSets.add(set1);
////            dataSets.add(set2);
//
//            BarData data = new BarData(dataSets);
//            data.setValueTextSize(10f);
//            data.setValueTypeface(mTfLight);
//            data.setBarWidth(barWidth);
//            horizontalBarChart.setData(data);
//        }
    }

    private int[] getColors() {

        int stacksize = 2;

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = ColorTemplate.MATERIAL_COLORS[i];
        }

        return colors;
    }

    protected RectF mOnValueSelectedRectF = new RectF();
    @SuppressLint("NewApi")
    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;

        RectF bounds = mOnValueSelectedRectF;
        horizontalBarChart.getBarBounds((BarEntry) e, bounds);

        MPPointF position = horizontalBarChart.getPosition(e, horizontalBarChart.getData().getDataSetByIndex(h.getDataSetIndex())
                .getAxisDependency());

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() {
    };

    protected void styleData(ChartData data) {
//        data.setValueTypeface(mTf);
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.DKGRAY);
        data.setValueFormatter(new PercentFormatter());
    }

}
