package com.lifeistech.android.testschedule;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


/**
 * Created by Masashi Hamaguchi on 2017/07/23.
 */

public class NavActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.mr_children);
        setContentView(imageView);

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}
