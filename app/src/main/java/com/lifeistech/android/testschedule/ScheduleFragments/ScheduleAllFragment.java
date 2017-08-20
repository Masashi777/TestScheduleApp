package com.lifeistech.android.testschedule.ScheduleFragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.lifeistech.android.testschedule.BaseFragment;
import com.lifeistech.android.testschedule.FragmentAdapter.SendTest;
import com.lifeistech.android.testschedule.MPAndroid.Example;
import com.lifeistech.android.testschedule.R;
import com.lifeistech.android.testschedule.TestClass.Subject;
import com.lifeistech.android.testschedule.TestClass.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Masashi Hamaguchi on 2017/07/30.
 */

public class ScheduleAllFragment extends BaseFragment implements OnChartValueSelectedListener {

    private PieChart mChart;

    private Test test;
    private List<Subject> subjectList;
    private String testName;


    public static Fragment newInstance() {

        return new ScheduleAllFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_schedule_all, null);

        // ひもづけ
        mChart = (PieChart) view.findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        // センターテキスト
        mChart.setCenterTextTypeface(mTfLight);
        mChart.setCenterText(generateCenterSpannableText());

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
        mChart.setOnChartValueSelectedListener(this);


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
//        Bundle bundle = getArguments();
//        test = (Test) bundle.getSerializable("test");

        // Parcelable
//        SendTest sendTest = (SendTest) bundle.getParcelable("test");
//        test = sendTest.getTestObject();

        subjectList = new List<Subject>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Subject> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] a) {
                return null;
            }

            @Override
            public boolean add(Subject subject) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Subject> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends Subject> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Subject get(int index) {
                return null;
            }

            @Override
            public Subject set(int index, Subject element) {
                return null;
            }

            @Override
            public void add(int index, Subject element) {

            }

            @Override
            public Subject remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Subject> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Subject> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<Subject> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
//        Log.e("TAG", test.getTestName());

//        for (int n = 0; n < test.getDateList().size(); n++) {
//            for (int m = 0; m < test.getDateList().get(n).getSubjectList().size(); m++) {
//                subjectList.add(test.getDateList().get(n).getSubjectList().get(m));
//                Log.e("name", test.getDateList().get(n).getSubjectList().get(m).getSubjectName());
//            }
//        }


        // 臨時
        Example example = new Example();
        Test test1, test2;
        test1 = example.getExam1();
        test2 = example.getExam2();

        subjectList = Arrays.asList(
                test1.getDateList().get(0).getSubjectList().get(0),
                test1.getDateList().get(0).getSubjectList().get(1),
                test1.getDateList().get(1).getSubjectList().get(0),
                test1.getDateList().get(1).getSubjectList().get(1),

                test2.getDateList().get(0).getSubjectList().get(0),
                test2.getDateList().get(0).getSubjectList().get(1),
                test2.getDateList().get(1).getSubjectList().get(0),
                test2.getDateList().get(1).getSubjectList().get(1)
        );

        testName = test1.getTestName();


        // グラフにデータをセット
        setData();


        return view;
    }

    private void setData() {

        Log.d("TAG", "TAG3");

//        Bundle bundle = getArguments();
//        date = (Date) bundle.getSerializable("data");

        // PieEntryを使ってentriesにデータをセット
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        try {
            for (int i = 0; i < subjectList.size(); i++) {
                entries.add(new PieEntry(subjectList.get(i).getPriority(), subjectList.get(i).getSubjectName()));
            }
        } catch (Exception e) {

        }


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

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString(testName + "\ndeveloped by Masashi Hamaguchi");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 5, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 5, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 5, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 20, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 19, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 19, s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

}
