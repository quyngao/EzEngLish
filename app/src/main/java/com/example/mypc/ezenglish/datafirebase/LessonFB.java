package com.example.mypc.ezenglish.datafirebase;

import com.example.mypc.ezenglish.model.Doc;
import com.example.mypc.ezenglish.model.History;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.MP3;
import com.example.mypc.ezenglish.model.Recod;
import com.example.mypc.ezenglish.model.Voca;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by Quylt on 8/24/2016.
 */
public class LessonFB {
    public int id;
    String name;
    String img;
    String context;
    int time;
    String start;
    List<VocaFB> vocas;
    List<MP3FB> all;
    DocFB doc;


    public LessonFB() {
    }

    public LessonFB(int id, String name, String img, String context, int time, String start, List<VocaFB> vocas, List<MP3FB> all, DocFB doc) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.context = context;
        this.time = time;
        this.start = start;
        this.vocas = vocas;
        this.all = all;
        this.doc = doc;
    }

    public LessonFB(Lesson l) {
        this.id = l.getId();
        this.name = l.getName();
        this.img = l.getImg();
        this.context = l.getContext();
        this.time = l.getTime();
        this.start = l.getStart();
        this.vocas = new ArrayList<>();
        for (Voca c : l.getVocas()) {
            vocas.add(new VocaFB(c));
        }
        this.all = new ArrayList<>();
        for (MP3 c : l.getAll()) {
            all.add(new MP3FB(c));
        }
        this.doc = new DocFB(l.getDoc());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getContext() {
        return context;
    }

    public int getTime() {
        return time;
    }

    public String getStart() {
        return start;
    }

    public List<VocaFB> getVocas() {
        return vocas;
    }

    public List<MP3FB> getAll() {
        return all;
    }

    public DocFB getDoc() {
        return doc;
    }
}
