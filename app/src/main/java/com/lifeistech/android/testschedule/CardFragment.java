package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.lifeistech.android.testschedule.TestClass.Date;
import com.lifeistech.android.testschedule.TestClass.Subject;
import com.lifeistech.android.testschedule.TestClass.Test;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.R.attr.name;

/**
 * Created by Masashi Hamaguchi on 2017/07/07.
 */

public class CardFragment extends Fragment {

    private TextView mTextView;

    private final static String KEY_NAME = "key_name";
    private final static String KEY_BACKGROUND = "key_background_color";

    private String mName = "";
    private @ColorInt int mBackgroundColor = Color.TRANSPARENT;

    private NumberPicker monthPicker, dayPicker, priority1, priority2, priority3, priority4;
    MaterialEditText editText, subject1, subject2, subject3, subject4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Bundleの値を受け取る際はonCreateメソッド内で行う
        Bundle args = getArguments();
        // Bundleがセットされていなかった時はNullなのでNullチェックをする
        if (args != null) {
            // String型でNameの値を受け取る
            mName = args.getString(KEY_NAME);
            // int型で背景色を受け取る
            mBackgroundColor = args.getInt(KEY_BACKGROUND, Color.TRANSPARENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        /**
         *Fragmentのレイアウトが作られるところ
         * ここでUIの初期設定をします
         * */

        //レイアウトをビューとして作成
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        // 最大値、最小値を設定
        Calendar cal = Calendar.getInstance();

        // NumberPicker のインスタンスを取得
        monthPicker = (NumberPicker) view.findViewById(R.id.monthPicker);
        dayPicker = (NumberPicker) view.findViewById(R.id.dayPicker);
        priority1 = (NumberPicker) view.findViewById(R.id.priority1);
        priority2 = (NumberPicker) view.findViewById(R.id.priority2);
        priority3 = (NumberPicker) view.findViewById(R.id.priority3);
        priority4 = (NumberPicker) view.findViewById(R.id.priority4);

        editText = (MaterialEditText) view.findViewById(R.id.TestEdit);
        subject1 = (MaterialEditText) view.findViewById(R.id.subject1);
        subject2 = (MaterialEditText) view.findViewById(R.id.subject2);
        subject3 = (MaterialEditText) view.findViewById(R.id.subject3);
        subject4 = (MaterialEditText) view.findViewById(R.id.subject4);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(cal.get(Calendar.MONTH));

        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(31);
        dayPicker.setValue(cal.get(Calendar.DAY_OF_MONTH));

        priority1.setMinValue(1);
        priority1.setMaxValue(5);
        priority2.setMinValue(1);
        priority2.setMaxValue(5);
        priority3.setMinValue(1);
        priority3.setMaxValue(5);
        priority4.setMinValue(1);
        priority4.setMaxValue(5);

        priority1.setValue(1);
        priority2.setValue(1);
        priority3.setValue(1);
        priority4.setValue(1);
        return view;
    }

    public void addData(String testName) {
        //TestClass
        Test test = new Test();
        test.setTestName(testName);

        //日付の設定
        Date day1 = new Date();
        day1.setMonth(monthPicker.getValue());
        day1.setDay(dayPicker.getValue());

        //Subjectの設定
        Subject subjectA = new Subject();
        Subject subjectB = new Subject();
        Subject subjectC = new Subject();
        Subject subjectD = new Subject();

        subjectA.setSubjectName(subject1.getText().toString());
        subjectB.setSubjectName(subject2.getText().toString());
        subjectC.setSubjectName(subject3.getText().toString());
        subjectD.setSubjectName(subject4.getText().toString());

        subjectA.setPriority(priority1.getValue());
        subjectB.setPriority(priority2.getValue());
        subjectC.setPriority(priority3.getValue());
        subjectD.setPriority(priority4.getValue());

        List<Subject> subjectList = new ArrayList<Subject>();
        subjectList.add(subjectA);
        subjectList.add(subjectB);
        subjectList.add(subjectC);
        subjectList.add(subjectD);

        List<Date> dateList = new ArrayList<Date>();
        dateList.add(day1);

        //TestClassへ追加
        day1.setSubjectList(subjectList);
        test.setDateList(dateList);

        //Intentの生成
        Intent intent = new Intent(getActivity(), ScheduleActivity.class);
        /**
         * testはオブジェクトなのでintentで渡すことはできません！プリミティブな変数しか渡せないよ！
         * 今回はそのままsetした内容をgetして送りました。
         * */
        intent.putExtra("test", test.getTestName());
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TextViewをひも付けます
        mTextView = (TextView) view.findViewById(R.id.textView);
        // Buttonのクリックした時の処理を書きます
//        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTextView.setText(mTextView.getText() + "!");
//            }
//        });

        // 背景色をセットする
        view.setBackgroundColor(mBackgroundColor);
        // onCreateで受け取った値をセットする
//        mTextView.setText(mName);
    }

    // このメソッドからFragmentを作成することを強制する
    @CheckResult
    public static CardFragment createInstance(String name, @ColorInt int color) {
        // Fragmentを作成して返すメソッド
        // createInstanceメソッドを使用することで、そのクラスを作成する際にどのような値が必要になるか制約を設けることができる
        CardFragment fragment = new CardFragment();
        // Fragmentに渡す値はBundleという型でやり取りする
        Bundle args = new Bundle();
        // Key/Pairの形で値をセットする
        args.putString(KEY_NAME, name);
        args.putInt(KEY_BACKGROUND, color);
        // Fragmentに値をセットする
        fragment.setArguments(args);
        return fragment;
    }
}
