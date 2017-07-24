package com.lifeistech.android.testschedule.TestClass;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Masashi Hamaguchi on 2017/07/17.
 */

public class Test implements Serializable {

    public String testName = "noneName";
    public List<Date> dateList;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<Date> getDateList() {
        return dateList;
    }

    public void setDateList(List<Date> dateList) {
        this.dateList = dateList;
    }
}
