package com.lifeistech.android.testschedule.Fragments_Detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lifeistech.android.testschedule.R;

/**
 * Created by Masashi Hamaguchi on 2017/07/26.
 */

public class DetailFrag_Categry extends Fragment {

    private TextView textView;
    private String title;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        title = bundle.getString("categoryName", "");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_detail_category, container, false);

        textView = (TextView) view.findViewById(R.id.textView);
        listView = (ListView) view.findViewById(R.id.detailListView);

        textView.setText(title);

//        ArrayList<Item> categoryItemList = new ArrayList<Item>();
//        for (int i = 0; i < )

        return view;
    }
}
