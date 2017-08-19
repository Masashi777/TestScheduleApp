package com.lifeistech.android.testschedule.TestClass;

import java.io.Serializable;

/**
 * Created by Masashi Hamaguchi on 2017/07/17.
 */

public class Subject implements Serializable {

    public String subjectName = "noneSubject";
    public int priority = 000;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
