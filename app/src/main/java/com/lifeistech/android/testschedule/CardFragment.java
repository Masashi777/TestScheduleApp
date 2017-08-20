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
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by Masashi Hamaguchi on 2017/07/07.
 */

public class CardFragment extends Fragment {

    private TextView mTextView;

    private final static String KEY_NAME = "key_name";
    private final static String KEY_BACKGROUND = "key_background_color";

    private String mName = "";
    private @ColorInt int mBackgroundColor = Color.TRANSPARENT;

    private Date date = new Date();

    private NumberPicker monthPicker, dayPicker, priority1, priority2, priority3, priority4;
    private NumberPicker[] priorityPickers = new NumberPicker[] {priority1, priority2, priority3, priority4};

    private MaterialEditText subject1, subject2, subject3, subject4;
    private MaterialEditText[] subjectEditTexts = new MaterialEditText[] {subject1, subject2, subject3, subject4};

    private Subject subjectA, subjectB, subjectC, subjectD;
    private Subject[] subjects = new Subject[] {subjectA, subjectB, subjectC, subjectD};


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
        View view = inflater.inflate(R.layout.frag_card, container, false);

        // 最大値、最小値を設定
        Calendar cal = Calendar.getInstance();

        // NumberPicker のインスタンスを取得
        monthPicker = (NumberPicker) view.findViewById(R.id.monthPicker);
        dayPicker = (NumberPicker) view.findViewById(R.id.dayPicker);
        priorityPickers[0] = (NumberPicker) view.findViewById(R.id.priority1);
        priorityPickers[1] = (NumberPicker) view.findViewById(R.id.priority2);
        priorityPickers[2] = (NumberPicker) view.findViewById(R.id.priority3);
        priorityPickers[3] = (NumberPicker) view.findViewById(R.id.priority4);

        subjectEditTexts[0] = (MaterialEditText) view.findViewById(R.id.subject1);
        subjectEditTexts[1] = (MaterialEditText) view.findViewById(R.id.subject2);
        subjectEditTexts[2] = (MaterialEditText) view.findViewById(R.id.subject3);
        subjectEditTexts[3] = (MaterialEditText) view.findViewById(R.id.subject4);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(cal.get(Calendar.MONTH));

        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(31);
        dayPicker.setValue(cal.get(Calendar.DAY_OF_MONTH));

        for (int i = 0; i > 4; i++) {
            priorityPickers[i].setMinValue(1);
            priorityPickers[i].setMaxValue(5);
        }

        for (int i = 0; i > 4; i++) {
            priorityPickers[i].setValue(1);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TextViewをひも付けます
        mTextView = (TextView) view.findViewById(R.id.textView);
        // Buttonのクリックした時の処理を書きます
//        view.findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addData();
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

    public void addData() {

        //日付の設定
        date.setMonth(monthPicker.getValue());
        date.setDay(dayPicker.getValue());

        //Subjectの設定
        List<Subject> subjectList = new ArrayList<Subject>();

        for (int i = 0; i < 4; i++) {

            if (subjectEditTexts[i].getText().toString().matches("")) {

                // no data

            } else {
                subjects[i].setSubjectName(subjectEditTexts[i].getText().toString());
                subjects[i].setPriority(priorityPickers[i].getValue());

                subjectList.add(subjects[i]);
            }

        }

        date.setSubjectList(subjectList);

        //Intentの生成
        Intent intent = new Intent(getActivity(), MakeActivity.class);
        /**
         * testはオブジェクトなのでintentで渡すことはできません！プリミティブな変数しか渡せないよ！
         * 今回はそのままsetした内容をgetして送りました。
         * */
        intent.putExtra("day", date);
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);
    }

}
