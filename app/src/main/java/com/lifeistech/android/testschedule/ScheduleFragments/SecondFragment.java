package com.lifeistech.android.testschedule.ScheduleFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lifeistech.android.testschedule.R;

/**
 * Created by Masashi Hamaguchi on 2017/07/26.
 */

public class SecondFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2, container, false);
    }
}
