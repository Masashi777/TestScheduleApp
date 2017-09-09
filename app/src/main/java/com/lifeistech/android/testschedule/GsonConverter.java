package com.lifeistech.android.testschedule;

import android.content.Context;
import android.content.SharedPreferences;

import com.lifeistech.android.testschedule.ItemClass.Category;
import com.lifeistech.android.testschedule.ItemClass.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_MULTI_PROCESS;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Masashi Hamaguchi on 2017/08/27.
 */

public class GsonConverter {


    // ItemList

    //要素群の書き込み
    public static void saveItems(Context context, ArrayList<Item> itemList) {
        //ArrayListをJSONに変換
        String json = item2json(itemList);

        //プリファレンスへの書き込み
        SharedPreferences pref = context.getSharedPreferences(
                "itemList", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("itemList", json);
        editor.commit();
    }

    //要素群の読み込み
    public static ArrayList<Item> loadItems(Context context) {
        //プリファレンスからの読み込み
        SharedPreferences pref = context.getSharedPreferences(
                "itemList", MODE_PRIVATE);
        String json = pref.getString("itemList","");

        //JSONをArrayListに変換
        ArrayList<Item> itemList;
        itemList = json2item(json);
        return itemList;
    }

    //ArrayListをJSONに変換
    private static String item2json(ArrayList<Item> items) {
        try {
            JSONArray array = new JSONArray();
            // java.util.ArrayList.iterator()' on a null object reference
            for (Item item : items) { // int i = 0; i < items.size(); i++) {
//                Item item = items.get(i);
                JSONObject obj = new JSONObject();
                obj.put("title", item.itemName);
                obj.put("checked", item.checked);
                array.put(obj);
            }
            return array.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    //JSONをArrayListに変換
    private static ArrayList<Item> json2item(String json) {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Item item = new Item();
                item.itemName = obj.getString("title");
                item.checked = obj.getBoolean("checked");
                items.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }


    // CategoryList

    //要素群の書き込み
    public static void saveCategories(Context context, ArrayList<Category> categoryList) {
        //ArrayListをJSONに変換
        String json = category2json(categoryList);

        //プリファレンスへの書き込み
//<<<<<<< HEAD
        SharedPreferences pref = context.getSharedPreferences("categoryList", MODE_PRIVATE);
//=======
//        SharedPreferences pref = context.getSharedPreferences(
//                "categoryList", MODE_MULTI_PROCESS);
//>>>>>>> dev2
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("categoryList", json);
        editor.commit();
    }

    //要素群の読み込み
    public static ArrayList<Category> loadCategories(Context context) {
        //プリファレンスからの読み込み
//<<<<<<< HEAD
        SharedPreferences pref = context.getSharedPreferences("categoryList", MODE_PRIVATE);
//=======
//        SharedPreferences pref = context.getSharedPreferences(
//                "categoryList", MODE_MULTI_PROCESS);
//>>>>>>> dev2
        String json = pref.getString("categoryList","");

        //JSONをArrayListに変換
        ArrayList<Category> categoryList;
        categoryList = json2category(json);
        return categoryList;
    }

    //ArrayListをJSONに変換
    private static String category2json(ArrayList<Category> categories) {
        try {
            JSONArray array = new JSONArray();
            for (int i = 0; i < categories.size(); i++) {
                JSONObject obj = new JSONObject();
                Category category = new Category();
                obj.put("title", category.categoryName);
                obj.put("task", category.task);
                obj.put("color", category.color);

                ArrayList<Item> items = categories.get(i).getItemList();

                if (items != null){
                    String itemString = item2json(items);
                    obj.put("item", itemString);

                }
                array.put(obj);
            }
            return array.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    //JSONをArrayListに変換
    private static ArrayList<Category> json2category(String json) {
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            JSONArray array = new JSONArray(json);
            for (int n = 0; n < array.length(); n++) {
                JSONObject obj = array.getJSONObject(n);
                Category category = new Category();
                category.categoryName = obj.getString("title");
                category.task = obj.getBoolean("task");
                category.color = obj.getInt("color");

                ArrayList<Item> items = new ArrayList<Item>();
                String itemString = obj.getString("item");
                items = json2item(itemString);

                category.itemList = items;

                categories.add(category);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categories;
    }

}
