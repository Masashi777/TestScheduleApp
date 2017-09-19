package com.lifeistech.android.testschedule.Adapter_ListView;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lifeistech.android.testschedule.R;

import java.util.ArrayList;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

/**
 * Created by Masashi Hamaguchi on 2017/07/22.
 */

public class CategoryListAdapter extends ArrayAdapter<String> {
    private ArrayList<String> categories = new ArrayList<String>();

    public final int[] MATERIAL_COLORS = {
            rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db"), rgb("#79b5e2")
    };

    public CategoryListAdapter(Context context, int layoutResourceId, ArrayList<String> objects) {
        super(context, layoutResourceId, objects);

        categories = objects;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public String getItem(int position) {
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

//        final Category category = getItem(position);

//        if (category != null) {
//            //set data
//            viewHolder.iconImage.setImageResource(R.drawable.ic_android_black_24dp);
//            viewHolder.categoryText.setText(category.getCategoryName());
//
//            switch (position) {
//                case 0:
//                    viewHolder.cardView.setCardBackgroundColor(MATERIAL_COLORS[0]);
//                    break;
//                case 1:
//                    viewHolder.cardView.setCardBackgroundColor(MATERIAL_COLORS[1]);
//                    break;
//                case 2:
//                    viewHolder.cardView.setCardBackgroundColor(MATERIAL_COLORS[2]);
//                    break;
//                case 3:
//                    viewHolder.cardView.setCardBackgroundColor(MATERIAL_COLORS[3]);
//                    break;
//                case 4:
//                    viewHolder.cardView.setCardBackgroundColor(MATERIAL_COLORS[4]);
//                    break;
//            }

//            if (category.getCategoryName() == "勉強") {
//                viewHolder.cardView.setCardBackgroundColor(MATERIAL_COLORS[0]);
//            } else if (category.getCategoryName() == "文化祭準備") {
//                viewHolder.cardView.setCardBackgroundColor(MATERIAL_COLORS[1]);
//            } else if (category.getCategoryName() == "遊び") {
//                viewHolder.cardView.setCardBackgroundColor(MATERIAL_COLORS[2]);
//            } else if (category.getCategoryName() == "買い物") {
//                viewHolder.cardView.setCardBackgroundColor(MATERIAL_COLORS[3]);
//            } else if (category.getCategoryName() == "家でやること") {
//                viewHolder.cardView.setCardBackgroundColor(MATERIAL_COLORS[4]);
//            }

//        }

        return convertView;
    }

    private class ViewHolder {
        ImageView iconImage;
        TextView categoryText;
        CardView cardView;

        public ViewHolder(View view) {
            //get instance
            iconImage = (ImageView) view.findViewById(R.id.iconImage);
            categoryText = (TextView) view.findViewById(R.id.categoryText);
            cardView = (CardView) view.findViewById(R.id.cardView);
        }
    }

}