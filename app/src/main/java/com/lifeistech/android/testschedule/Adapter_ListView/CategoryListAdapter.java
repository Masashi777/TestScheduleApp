package com.lifeistech.android.testschedule.Adapter_ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lifeistech.android.testschedule.ItemClass.Category;
import com.lifeistech.android.testschedule.ItemClass.Item;
import com.lifeistech.android.testschedule.R;

import java.util.ArrayList;

/**
 * Created by Masashi Hamaguchi on 2017/07/22.
 */

public class CategoryListAdapter extends ArrayAdapter<Category> {
    private ArrayList<Category> categories = new ArrayList<Category>();

    public CategoryListAdapter(Context context, int layoutResourceId, ArrayList<Category> objects) {
        super(context, layoutResourceId, objects);

        categories = objects;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Category getItem(int position) {
        return categories.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_category, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder); //ここでtagを設定しておかないと落ちる
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Category category = getItem(position);

        if (category != null) {
            //set data
            viewHolder.iconImage.setImageResource(R.drawable.ic_android_black_24dp);
            viewHolder.categoryText.setText(category.getCategoryName());
        }

        return convertView;
    }

    private class ViewHolder {
        ImageView iconImage;
        TextView categoryText;

        public ViewHolder(View view) {
            //get instance
            iconImage = (ImageView) view.findViewById(R.id.iconImage);
            categoryText = (TextView) view.findViewById(R.id.categoryText);
        }
    }

}