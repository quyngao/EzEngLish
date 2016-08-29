package com.example.mypc.ezenglish.model;

import java.io.Serializable;

/**
 * Created by MyPC on 21/08/2016.
 */
public class VocaData implements Serializable {
    public int id;
    String name;
    String description;
    String img;
    String trans;
    String mean;
    String type;
    boolean isRed;

    public void setType(String type) {
        this.type = type;
    }

    public void setMean(String mean) {

        this.mean = mean;
    }

    public String getMean() {

        return mean;
    }

    public String getType() {
        return type;
    }

    public void copyData(Voca x) {
        this.id = x.getId();
        this.name = x.getName();
        this.description = x.getDescription();
        this.img = x.getImg();
        this.trans = x.getTrans();
        this.mean = x.getMean();
        this.type = x.getType();

        this.type = x.getTrans();
        this.isRed = true;

    }

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public String getTrans() {
        return trans;
    }
}
