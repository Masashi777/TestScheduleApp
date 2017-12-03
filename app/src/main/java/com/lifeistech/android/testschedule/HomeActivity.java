package com.lifeistech.android.testschedule;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.facebook.stetho.Stetho;
import com.lifeistech.android.testschedule.ItemClass.Item;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.ArrayList;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class HomeActivity extends AppCompatActivity {

    // Layout
    private ConstraintLayout constraintLayout;
    private ListView listView;
    private EditText editText;
    private Button button;

    // UI
    private ArrayList<Item> itemList; //要素群
    private ArrayList<String> nameList;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // ひもずけ
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);


        // Realm全体の初期化
        realm.init(getApplicationContext());
        // このスレッドのためのRealmインスタンスを取得
        realm = Realm.getDefaultInstance();

        // OnClickListener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().toString().matches("")) {
                    // 何もしない
                    Snackbar.make(constraintLayout, "入力してください。", Snackbar.LENGTH_SHORT)
                            .show();

                } else {
                    // 通常のJavaオブジェクトのようにモデルクラスを使用

                    final Item item = new Item();
                    item.setItemName(editText.getText().toString());
                    item.setPRIMARY_KEY(getPrimaryKey());
                    item.setCategory("Category");
                    item.setChecked(true);
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealmOrUpdate(item);
                        }
                    });

                    editText.setText("");
                }

            }
        });


        // すべてのオブジェクトを取得する問い合わせを発行
        final RealmResults<Item> items = realm.where(Item.class).findAll();
//        items.size(); // => まだRealmへは一つもオブジェクトが保存されていないので0を返します

//        try {
//            for (int i = 0; i < items.size(); i++) {
//                itemList.add(items.get(i));
//            }
//        } catch (Exception e) {
//
//        }


        final ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.list_item, items);

        listView.setAdapter(itemAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                // クエリを発行し結果を取得
                final RealmResults<Item> deleteResults = realm.where(Item.class).findAll();

                // 変更操作はトランザクションの中で実行する必要あり
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        // マッチしたオブジェクトから１つを削除
                        deleteResults.deleteFromRealm(position);
//                        deleteResults.deleteLastFromRealm();

                        // 特定のオブジェクトのみを削除
//                        Dog dog = deleteResults.get(5);
//                        dog.deleteFromRealm();

                        // すべてのオブジェクトを削除
//                        deleteResults.deleteFromRealm();
                    }
                });

                return false;
            }
        });

        // データが更新されると、リスナーが呼びだされて更新を受け取ることができます。
        items.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Item>>() {
            @Override
            public void onChange(RealmResults<Item> results, OrderedCollectionChangeSet changeSet) {
                // Query results are updated in real time with fine grained notifications.
                changeSet.getInsertions(); // => [0] is added.

                // すべてのオブジェクトを取得する問い合わせを発行
                final RealmResults<Item> items = realm.where(Item.class).findAll();
                items.size(); // => まだRealmへは一つもオブジェクトが保存されていないので0を返します

                listView.setAdapter(itemAdapter);
            }
        });


        // タイトルの設定
        setTitle("Realm for Android");

        // Stetho
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        // 以下の行が変更点
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

    }

    /**
     * UserのプライマリキーuserIdの最大値をインクリメントした値を取得する。
     * Userが1度も作成されていなければ0を取得する。
     */
    private long getPrimaryKey() {
        // 初期化
        long newPrimaryKey = 0;
        // userIdの最大値を取得
        Number maxUserId = realm.where(Item.class).max("PRIMARY_KEY");
        // 1度もデータが作成されていない場合はNULLが返ってくるため、NULLチェックをする
        if(maxUserId != null) {
            newPrimaryKey = maxUserId.intValue() + 1;
        }
        return newPrimaryKey;
    }

}