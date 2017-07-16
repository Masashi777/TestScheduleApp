package com.lifeistech.android.testschedule.TestClass;

import java.util.List;

/**
 * Created by Masashi Hamaguchi on 2017/07/17.
 */

public class Date {

    public int month;
    public int day;
    public String week;
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
