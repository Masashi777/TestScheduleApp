package com.lifeistech.android.testschedule.ItemClass;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Masashi Hamaguchi on 2017/08/25.
 */

public class Item extends RealmObject implements Serializable {

    @PrimaryKey
    private long PRIMARY_KEY;
    private String itemName;
    private String category;
    private boolean checked;



    public long getPRIMARY_KEY() {
        return PRIMARY_KEY;
    }

    public void setPRIMARY_KEY(long PRIMARY_KEY) {
        this.PRIMARY_KEY = PRIMARY_KEY;
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
