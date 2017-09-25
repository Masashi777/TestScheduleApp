package com.lifeistech.android.testschedule.ItemClass;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Masashi Hamaguchi on 2017/08/25.
 */

public class Item extends RealmObject {

    @PrimaryKey
    public String key;
    public String itemName;
    public String category;
    public boolean checked;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}