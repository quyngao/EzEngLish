package com.example.mypc.ezenglish.model;

import java.util.Date;
import java.util.List;

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
    Date start;
    int isrealy;
    RealmList<Recod> recods;
    RealmList<History> histories;
    RealmList<Voca> vocas;
    RealmList<MP3> all;

    Doc doc;
    @Ignore
    RealmList<Schedule> schedules;
    @Ignore
    List<MP3> ministore;
    @Ignore
    MP3 audio;
    @Ignore
    MP3 vocamp3;
    @Ignore
    Topic topic;
    @Ignore
    User user;

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

    public void setStart(Date start) {
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

    public void setMinistore(List<MP3> ministore) {
        this.ministore = ministore;
    }

    public void setAudio(MP3 audio) {
        this.audio = audio;
    }

    public void setVocamp3(MP3 vocamp3) {
        this.vocamp3 = vocamp3;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
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

    public Date getStart() {
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

    public List<MP3> getMinistore() {
        return ministore;
    }

    public MP3 getAudio() {
        return audio;
    }

    public MP3 getVocamp3() {
        return vocamp3;
    }

    public Topic getTopic() {
        return topic;
    }

    public User getUser() {
        return user;
    }


}
