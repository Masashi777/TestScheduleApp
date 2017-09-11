package com.lifeistech.android.testschedule.ItemClass;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Masashi Hamaguchi on 2017/08/25.
 */

public class Item extends RealmObject implements Serializable {

    public String itemName;
    public boolean checked;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
