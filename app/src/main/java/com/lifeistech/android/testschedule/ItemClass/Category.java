package com.lifeistech.android.testschedule.ItemClass;

import android.graphics.Color;
import android.support.annotation.ColorInt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Masashi Hamaguchi on 2017/08/25.
 */

public class Category implements Serializable {
    public String categoryName;
    public boolean task;
    public int color = Color.YELLOW;
    public ArrayList<Item> itemList;

//    public static Category addCategory(String categoryName, boolean task, String icon) {
//        Category category = new Category();
//        category.setCategoryName(categoryName);
//        category.setTask(task);
//        category.setIcon(icon);
//
//        return category;
//    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isTask() {
        return task;
    }

    public void setTask(boolean task) {
        this.task = task;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
}
