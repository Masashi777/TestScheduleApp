package com.lifeistech.android.testschedule.Fragments_Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.lifeistech.android.testschedule.Adapter_ListView.CategoryListAdapter;
import com.lifeistech.android.testschedule.BaseFragment;
import com.lifeistech.android.testschedule.CategoryEditActivity;
import com.lifeistech.android.testschedule.GsonConverter;
import com.lifeistech.android.testschedule.ItemClass.Category;
import com.lifeistech.android.testschedule.R;

import java.util.ArrayList;

public class HomeThirdFragment extends BaseFragment {

    private ListView catListView;
    private Button newCatBtn;
    private CategoryListAdapter categoryListAdapter;
    private ArrayList<Category> categoryList = new ArrayList<Category>();

    private String[] categories = {"遊び", "勉強", "マンガを読む", "部活", "趣味", "テスト勉強"};
    private boolean[] checkList = {false, true, false, true, false, true};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 臨時データ
         */
//        for (int i = 0; i < categories.length; i++) {
//            categoryList.add(Category.addCategory(categories[i], checkList[i], "0"));
//        }

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_home_third, container, false);

        catListView = (ListView) view.findViewById(R.id.categoryListView);
        newCatBtn = (Button) view.findViewById(R.id.newCatBtn);

        // データの取得
        categoryList = GsonConverter.loadCategories(getContext());


        // Adapter
        categoryListAdapter = new CategoryListAdapter(getContext(), R.layout.list_category, categoryList);
        catListView.setAdapter(categoryListAdapter);

        newCatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryEditActivity.class);
                startActivity(intent);
            }
        });

        // リストへのボタンの配置
        catListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), CategoryEditActivity.class);
                intent.putExtra("category", categoryList.get(position));
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        catListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                categoryList.remove(position);
                categoryListAdapter.notifyDataSetChanged();

                return false;
            }
        });

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //要素群の書き込み
        GsonConverter.saveCategories(getContext(), categoryList);

    }


}