package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Masashi Hamaguchi on 2017/07/23.
 */

public class NavActivity extends BaseActivity {

    private TextView textView;
    private Button button;
    private String infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation);

        textView = (TextView) findViewById(R.id.info);
        button = (Button) findViewById(R.id.button);

        infoText = "Copyright © 2017\n" +
                "Masashi Hamaguchi\n" +
                "All Rights Reserved\n" +
                "\n" +
                "SPECIAL THANKS\n" +
                "Supported by\n" +
                "Life is Tech Meetan!";

        SpannableString string = new SpannableString(infoText);
        string.setSpan(new RelativeSizeSpan(1.3f), 16, 34, 0);
        string.setSpan(new RelativeSizeSpan(1.3f), 96, 104, 0);
        string.setSpan(new ForegroundColorSpan(Color.parseColor("#448AFF")), 16, 34, 0);
        string.setSpan(new ForegroundColorSpan(Color.parseColor("#E91E63")), 97, 104, 0);

        textView.setText(string);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

}
