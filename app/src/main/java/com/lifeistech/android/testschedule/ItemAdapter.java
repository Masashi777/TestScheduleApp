package com.lifeistech.android.testschedule;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lifeistech.android.testschedule.ItemClass.Item;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Masashi Hamaguchi on 2017/12/03.
 */

public class ItemAdapter extends ArrayAdapter<Item>{

    List<Item> itemList;
    Realm realm;

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
        final ViewHolder viewHolder;

        // Realm全体の初期化
        realm.init(getContext());
        // このスレッドのためのRealmインスタンスを取得
        realm = Realm.getDefaultInstance();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Item item = getItem(position);

        if (item == null) {
            // set data
            viewHolder.itemText.setText(item.getItemName());
            Log.e("Item Name", item.getItemName());
            viewHolder.categoryText.setText(item.getCategory());
            viewHolder.checkBox.setChecked(item.isChecked());

            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // クエリを発行し結果を取得
                    final RealmResults<Item> deleteResults = realm.where(Item.class).equalTo("PRIMARY_KEY", item.getPRIMARY_KEY()).findAll();

                    // 変更操作はトランザクションの中で実行する必要あり
                    final Item changeItem = deleteResults.first();
                    changeItem.setChecked(!viewHolder.checkBox.isChecked());
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealmOrUpdate(changeItem);
                        }
                    });

                    viewHolder.checkBox.setChecked(!viewHolder.checkBox.isChecked());
                }
            });
        }
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
