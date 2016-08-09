package com.example.mypc.ezenglish.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class Topic extends RealmObject {
    @PrimaryKey
    public int id;
    String name;
    String img;
    String description;
    RealmList<Lesson> lessons;

    public RealmList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(RealmList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Topic dummy(){
        MP3 m1 = new MP3();
        m1.setName("A Kiss Audio");
        m1.setContext("/storage/emulated/0/original/A Kiss Audio.mp3");
        m1.setId(1);
        m1.setLocation("/storage/emulated/0/original/A Kiss Audio.mp3");
/*
*
*
* 08-10 00:32:43.541 9237-9237/? D/path: /storage/emulated/0/original/A Kiss Audio.mp3
08-10 00:32:43.541 9237-9237/? E/path: /storage/emulated/0/original/A Kiss Audio.mp3
08-10 00:32:43.541 9237-9237/? D/path: /storage/emulated/0/original/A Kiss MS-A.mp3
08-10 00:32:43.541 9237-9237/? E/path: /storage/emulated/0/original/A Kiss MS-A.mp3
08-10 00:32:43.541 9237-9237/? D/path: /storage/emulated/0/original/A Kiss MS-B.mp3
08-10 00:32:43.541 9237-9237/? E/path: /storage/emulated/0/original/A Kiss MS-B.mp3
08-10 00:32:43.541 9237-9237/? D/path: /storage/emulated/0/original/A Kiss Vocab.mp3
08-10 00:32:43.541 9237-9237/? E/path: /storage/emulated/0/original/A Kiss Vocab.mp3
08-10 00:32:43.541 9237-9237/? D/path: /storage/emulated/0/original/Day of the Dead Audio.mp3
08-10 00:32:43.541 9237-9237/? E/path: /storage/emulated/0/original/Day of the Dead Audio.mp3
08-10 00:32:43.541 9237-9237/? D/path: /storage/emulated/0/original/Day of the Dead MS.mp3
08-10 00:32:43.541 9237-9237/? E/path: /storage/emulated/0/original/Day of the Dead MS.mp3
08-10 00:32:43.541 9237-9237/? D/path: /storage/emulated/0/original/Bubba's Food Audio.mp3
08-10 00:32:43.541 9237-9237/? E/path: /storage/emulated/0/original/Bubba's Food Audio.mp3
08-10 00:32:43.542 9237-9237/? D/path: /storage/emulated/0/original/Bubba's Food MS-A.mp3
08-10 00:32:43.542 9237-9237/? E/path: /storage/emulated/0/original/Bubba's Food MS-A.mp3
08-10 00:32:43.542 9237-9237/? D/path: /storage/emulated/0/original/Bubba's Food MS-B.mp3
08-10 00:32:43.542 9237-9237/? E/path: /storage/emulated/0/original/Bubba's Food MS-B.mp3
08-10 00:32:43.542 9237-9237/? D/path: /storage/emulated/0/original/Bubba's Food MS-C.mp3
08-10 00:32:43.542 9237-9237/? E/path: /storage/emulated/0/original/Bubba's Food MS-C.mp3
08-10 00:32:43.542 9237-9237/? D/path: /storage/emulated/0/original/Bubba's Food Vocab.mp3
08-10 00:32:43.542 9237-9237/? E/path: /storage/emulated/0/original/Bubba's Food Vocab.mp3
* */
return new Topic();
    }
}
