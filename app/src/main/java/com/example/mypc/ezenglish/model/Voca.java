package com.example.mypc.ezenglish.model;

import com.example.mypc.ezenglish.datafirebase.VocaFB;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Voca extends RealmObject {
    @PrimaryKey
    public int id;
    String name;
    String description;
    String img;
    String trans;

    public Voca() {
    }

    public Voca(VocaFB vc) {
        this.id = vc.getId();
        this.name = vc.getName();
        this.description = vc.getDescription();
        this.img = vc.getImg();
        this.trans = vc.getTrans();
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
