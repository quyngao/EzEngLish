package com.example.mypc.ezenglish.datafirebase;

import com.example.mypc.ezenglish.model.Voca;

/**
 * Created by Quylt on 8/24/2016.
 */
public class VocaFB {
    public int id;
    String name;
    String description;
    String img;
    String trans;
    String mean;
    String type;

    public VocaFB() {
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

    public void setMean(String mean) {
        this.mean = mean;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getMean() {
        return mean;
    }

    public String getType() {
        return type;
    }

    public VocaFB(Voca vc) {

        this.id = vc.getId();
        this.name = vc.getName();
        this.description = vc.getDescription();
        this.img = vc.getImg();
        this.trans = vc.getTrans();
        this.mean = vc.getMean();
        this.type = vc.getType();
    }
}
