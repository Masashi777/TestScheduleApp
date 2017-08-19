package com.lifeistech.android.testschedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lifeistech.android.testschedule.TestClass.Test;

import java.util.List;

/**
 * Created by Masashi Hamaguchi on 2017/07/22.
 */

public class TestListAdapter extends ArrayAdapter<Test> {
    List<Test> testList;

    public TestListAdapter(Context context, int layoutResourceId, List<Test> objects) {
        super(context, layoutResourceId, objects);

        testList = objects;
    }

    @Override
    public int getCount() {
        return testList.size();
    }

    @Override
    public Test getItem(int position) {
        return testList.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.test_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder); //ここでtagを設定しておかないと落ちる
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Test item = getItem(position);

        if (item != null) {
            //set data
//            viewHolder.dateText.setText(
//                    item.getDateList().get(0).getMonth() + "月" +
//                    item.getDateList().get(0).getDay() + "日 ~ " +
//                    item.getDateList().get(item.getDateList().size()).getMonth() + "月" +
//                    item.getDateList().get(item.getDateList().size()).getDay() + "日");
            viewHolder.nameText.setText(item.getTestName());
            viewHolder.commentText.setText(item.getComment());
        }

        return convertView;
    }

    private class ViewHolder {
        TextView dateText, nameText, commentText;

        public ViewHolder(View view) {
            //get instance
            dateText = (TextView) view.findViewById(R.id.dateText);
            nameText = (TextView) view.findViewById(R.id.nameText);
            commentText = (TextView) view.findViewById(R.id.commentText);
        }
    }

}