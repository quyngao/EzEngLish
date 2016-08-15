package com.example.mypc.ezenglish.model;

import java.io.Serializable;

/**
 * Created by Quylt on 8/15/2016.
 */
public class VocaRed implements Serializable {
    int step;
    boolean isalarm;
    boolean issound;
    String startdate;
    String enddate;

    public VocaRed() {
        step=5;
        startdate ="00:00";
        enddate="23:59";
        isalarm=false;
        issound= false;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setIsalarm(boolean isalarm) {
        this.isalarm = isalarm;
    }

    public void setIssound(boolean issound) {
        this.issound = issound;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public int getStep() {
        return step;
    }

    public boolean isalarm() {
        return isalarm;
    }

    public boolean issound() {
        return issound;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }
}
