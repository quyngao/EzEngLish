package com.example.mypc.ezenglish.model;

import com.example.mypc.ezenglish.datafirebase.LessonFB;
import com.example.mypc.ezenglish.datafirebase.MP3FB;
import com.example.mypc.ezenglish.datafirebase.VocaFB;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Lesson extends RealmObject {
    @PrimaryKey
    public int id;
    String name;
    String img;
    String context;
    int time;
    String start;
    int isrealy;
    RealmList<Recod> recods;
    RealmList<History> histories;
    RealmList<Voca> vocas;
    RealmList<MP3> all;
    Doc doc;

    @Ignore
    RealmList<Schedule> schedules;
    @Ignore
    User user;

    public Lesson() {
    }

    public Lesson(LessonFB l) {
        this.id = l.getId();
        this.name = l.getName();
        this.img = l.getImg();
        this.context = l.getContext();
        this.time = 0;
        this.isrealy = 0;
//        if (l.getId() > 5) this.isrealy = 0;
//        else if (l.getId() == 5) this.isrealy = 2;
//        else this.isrealy = 1;
        this.vocas = new RealmList<>();
        for (VocaFB vc : l.getVocas()) {
            this.vocas.add(new Voca(vc));
        }
        this.all = new RealmList<>();
        for (MP3FB m : l.getAll()) {
            this.all.add(new MP3(m));
        }
        this.doc = new Doc(l.getDoc());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setIsrealy(int isrealy) {
        this.isrealy = isrealy;
    }

    public void setRecods(RealmList<Recod> recods) {
        this.recods = recods;
    }

    public void setHistories(RealmList<History> histories) {
        this.histories = histories;
    }

    public void setVocas(RealmList<Voca> vocas) {
        this.vocas = vocas;
    }

    public void setAll(RealmList<MP3> all) {
        this.all = all;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public void setSchedules(RealmList<Schedule> schedules) {
        this.schedules = schedules;
    }


    public void setUser(User user) {
        this.user = user;
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

    public int getIsrealy() {
        return isrealy;
    }

    public RealmList<Recod> getRecods() {
        return recods;
    }

    public RealmList<History> getHistories() {
        return histories;
    }

    public RealmList<Voca> getVocas() {
        return vocas;
    }

    public RealmList<MP3> getAll() {
        return all;
    }

    public Doc getDoc() {
        return doc;
    }

    public RealmList<Schedule> getSchedules() {
        return schedules;
    }


    public User getUser() {
        return user;
    }


}
