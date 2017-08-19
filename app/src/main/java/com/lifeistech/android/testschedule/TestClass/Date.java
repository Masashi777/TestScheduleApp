package com.lifeistech.android.testschedule.TestClass;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Masashi Hamaguchi on 2017/07/17.
 */

public class Date implements Serializable {

    public int month = 00;
    public int day = 00;
    public String week = "noneWeek";
    public List<Subject> subjectList;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }
}
