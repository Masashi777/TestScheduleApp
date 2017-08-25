package com.lifeistech.android.testschedule.Adapter_ListView;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
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

public class ItemListAdapter extends ArrayAdapter<Item> {

    private ArrayList<Item> itemList = new ArrayList<Item>();
    private ArrayList<Category> categoryList = new ArrayList<Category>();

    public ItemListAdapter(Context context, int layoutResourceId, ArrayList<Item> objects) {
        super(context, layoutResourceId, objects);

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
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder); //ここでtagを設定しておかないと落ちる
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Item item = getItem(position);

        if (item != null) {
            //set data
            viewHolder.iconImage.setImageResource(R.drawable.ic_android_black_24dp);
            viewHolder.itemText.setText(item.getItemName());
            viewHolder.checkBox.setChecked(item.isChecked());
        }

        // カテゴリ別に色分け
//        viewHolder.cardView.setBackgroundColor(categoryList.get(item.getCategory()).getColor);

        return convertView;
    }

    private class ViewHolder {
        ImageView iconImage;
        TextView itemText;
        CheckBox checkBox;

        CardView cardView;

        public ViewHolder(View view) {
            //get instance
            iconImage = (ImageView) view.findViewById(R.id.iconImage);
            itemText = (TextView) view.findViewById(R.id.itemText);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);

            cardView = (CardView) view.findViewById(R.id.cardView);
        }
    }

}