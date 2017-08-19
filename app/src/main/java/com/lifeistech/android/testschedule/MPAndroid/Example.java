package com.lifeistech.android.testschedule.MPAndroid;

import com.lifeistech.android.testschedule.TestClass.Date;
import com.lifeistech.android.testschedule.TestClass.Subject;
import com.lifeistech.android.testschedule.TestClass.Test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Masashi Hamaguchi on 2017/08/19.
 */

public class Example implements Serializable {

    public Test exam1, exam2;

    public Date day1, day2, day3, day4;

    public Subject subject1, subject2, subject3, subject4, subject5, subject6, subject7, subject8;

    public List<Date> dateList1, dateList2;
    public List<Subject> subjectList1, subjectList2, subjectList3, subjectList4;


    public Test getExam1() {

        subject1 = new Subject();
        subject2 = new Subject();
        subject3 = new Subject();
        subject4 = new Subject();

        // Subject
        subject1.setSubjectName("数Ⅲ");
        subject2.setSubjectName("日本語特論");
        subject3.setSubjectName("日本史");
        subject4.setSubjectName("化学");

        subject1.setPriority(1);
        subject2.setPriority(2);
        subject3.setPriority(3);
        subject4.setPriority(4);


        // SubjectList
        subjectList1 = Arrays.asList(subject1, subject2);
        subjectList2 = Arrays.asList(subject3, subject4);


        // Day
        day1 = new Date();
        day2 = new Date();

        day1.setMonth(7);
        day1.setDay(7);
        day1.setWeek("week");
        day1.setSubjectList(subjectList1);

        day2.setMonth(7);
        day2.setDay(8);
        day2.setWeek("week");
        day2.setSubjectList(subjectList2);


        // DataList
        dateList1 = Arrays.asList(day1, day2);


        // Exam
        exam1 = new Test();

        exam1.setTestName("exam1");
        exam1.setComment("テスト頑張ろう！");
        exam1.setDateList(dateList1);


        return exam1;
    }


    public Test getExam2() {

        // Subject
        subject5 = new Subject();
        subject6 = new Subject();
        subject7 = new Subject();
        subject8 = new Subject();

        subject5.setSubjectName("物理");
        subject6.setSubjectName("政治・経済");
        subject7.setSubjectName("コミュ英");
        subject8.setSubjectName("総合英語");

        subject5.setPriority(1);
        subject6.setPriority(2);
        subject7.setPriority(3);
        subject8.setPriority(4);


        // SubjectList
        subjectList3 = Arrays.asList(subject5, subject6);
        subjectList4 = Arrays.asList(subject7, subject8);


        // Day
        day3 = new Date();
        day4 = new Date();

        day3.setMonth(7);
        day3.setDay(9);
        day3.setWeek("week");
        day3.setSubjectList(subjectList3);

        day4.setMonth(7);
        day4.setDay(10);
        day4.setWeek("week");
        day4.setSubjectList(subjectList4);


        // DateList
        dateList2 = Arrays.asList(day3, day4);

        // Exam
        exam2 = new Test();

        exam2.setTestName("exam2");
        exam2.setComment("test comment");
        exam2.setDateList(dateList2);


        return exam2;
    }




}
