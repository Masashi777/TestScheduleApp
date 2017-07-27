package com.lifeistech.android.testschedule;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Masashi Hamaguchi on 2017/07/26.
 */

public class MainFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";

    public static MainFragment newInstance(@ColorRes int IdRes) {
        MainFragment frag = new MainFragment();
        Bundle b = new Bundle();
        b.putInt(BACKGROUND_COLOR, IdRes);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.sample_fragment);
        linearLayout.setBackgroundResource(getArguments().getInt(BACKGROUND_COLOR));

        return view;
    }
}