package com.lifeistech.android.testschedule;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        //レイアウトをビューとして作成
        return inflater.inflate(R.layout.fragment_card, container, false);
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
