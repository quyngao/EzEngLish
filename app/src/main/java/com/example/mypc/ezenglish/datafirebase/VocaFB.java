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

    public VocaFB() {
    }

    public VocaFB(int id, String name, String description, String img, String trans) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img = img;
        this.trans = trans;
    }

    public VocaFB(Voca v) {
        this.id = v.getId();
        this.name = v.getName();
        this.description = v.getDescription();
        this.img = v.getImg();
        this.trans = v.getTrans();
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
