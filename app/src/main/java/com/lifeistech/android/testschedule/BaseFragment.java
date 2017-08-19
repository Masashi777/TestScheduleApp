package com.lifeistech.android.testschedule;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.constraint.solver.SolverVariable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ThemedSpinnerAdapter;

/**
 * Created by Masashi Hamaguchi on 2017/07/30.
 */

public abstract class BaseFragment extends Fragment {

    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

    protected String[] mParties = new String[] {
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    protected String[] popColor = new String[] {
            "#de9610", "#c93a40", "#fff001", "#d06d8c", "#65ace4", "#a0c238", "#56a764", "#d16b16",
            "#cc528b", "#9460a0", "#f2cf01", "#0074bf"
    };

    protected String[] casualColor = new String[] {
            "#7b9ad0", "#f8e352", "#c8d627", "#d5848b", "#e5ab47", "#e1cea3", "#51a1a2", "#b1d7e4",
            "#66b7ec", "#c08e47", "#ae8dbc", "#c3cfa9"
    };

    protected Typeface mTfRegular;
    protected Typeface mTfLight;

    protected Typeface KFhimaji;
    protected Typeface YuGothM;
    protected Typeface ikamodoki;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
//        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
    }

    protected float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }


}
