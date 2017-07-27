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

public class TestListAdapter extends ArrayAdapter<TestList> {
    List<TestList> mTestLists;

    public TestListAdapter(Context context, int layoutResourceId, List<TestList> objects) {
        super(context, layoutResourceId, objects);

        mTestLists = objects;
    }

    @Override
    public int getCount() {
        return mTestLists.size();
    }

    @Override
    public TestList getItem(int position) {
        return mTestLists.get(position);
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

        final TestList item = getItem(position);

        if (item != null) {
            //set data
            viewHolder.dateText.setText(item.date);
            viewHolder.nameText.setText(item.name);
            viewHolder.commentText.setText(item.comment);
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