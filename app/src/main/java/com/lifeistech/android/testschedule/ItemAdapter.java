package com.lifeistech.android.testschedule;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lifeistech.android.testschedule.ItemClass.Item;

import java.util.List;

/**
 * Created by Masashi Hamaguchi on 2017/12/03.
 */

public class ItemAdapter extends ArrayAdapter<Item>{

    List<Item> itemList;

    public ItemAdapter(Context context, int textViewResourceID, List<Item> objects) {
        super(context, textViewResourceID, objects);

        itemList = objects;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Item getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHlder;

        return convertView;
    }

    public static class ViewHolder {

        TextView itemText, categoryText;
        CheckBox checkBox;

        public ViewHolder(View view) {
            itemText = (TextView) view.findViewById(R.id.itemText);
            categoryText = (TextView) view.findViewById(R.id.categoryText);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        }

    }
}
