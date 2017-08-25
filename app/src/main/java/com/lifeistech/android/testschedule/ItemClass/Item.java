package com.lifeistech.android.testschedule.ItemClass;

import java.io.Serializable;

/**
 * Created by Masashi Hamaguchi on 2017/08/25.
 */

public class Item implements Serializable {

    public String itemName;
    public int category;
    public boolean checked;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
