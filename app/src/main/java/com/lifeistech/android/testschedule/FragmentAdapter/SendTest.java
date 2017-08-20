package com.lifeistech.android.testschedule.FragmentAdapter;

import android.os.Parcel;
import android.os.Parcelable;

import com.lifeistech.android.testschedule.TestClass.Test;

import java.io.Serializable;

/**
 * Created by Masashi Hamaguchi on 2017/08/20.
 */

public class SendTest implements Serializable, Parcelable {

    //Test用Parcelable

    // 運搬したいデータ
    private Object[] test = new Object[1];

    /** 空っぽの状態でインスタンス作成 */
    public SendTest() {
        // TODO 自動生成されたコンストラクター・スタブ
    }


    /** Parcel化予定の値をset */
    public void setTestObject(Test test) {
        this.test[0] = test;
    }


    /** Parcel化された値をget */
    public Test getTestObject() {
        return (Test) test[0];
    }

    // ----- Parcelableインタフェースが利用 ここから -----

    /** データのParcel化を実行する */
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeArray(test);
    }

    /** Parcelからデータを読み出すprivateコンストラクタ */
    private SendTest(Parcel in) {
        test = in.readArray(Test.class.getClassLoader());
    }

    /** ParcelからこのParcelableのインスタンスを作るためのCreator */
    public static final Parcelable.Creator<SendTest> CREATOR
            = new Parcelable.Creator<SendTest>() {
        public SendTest createFromParcel(Parcel in) {
            return new SendTest(in);
        }

        public SendTest[] newArray(int size) {
            return new SendTest[size];
        }
    };

    /** シリアライズされたオブジェクトの種類を判別するためのビットマスクを返す */
    @Override
    public int describeContents() {
        return 0;
    }

    // ----- Parcelableインタフェースが利用 ここまで -----

}
