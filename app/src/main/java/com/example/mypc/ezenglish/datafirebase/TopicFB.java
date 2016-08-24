package com.example.mypc.ezenglish.datafirebase;

import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.Topic;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by Quylt on 8/24/2016.
 */
public class TopicFB {
    int id;
    String name;
    String img;
    String description;
    List<LessonFB> lessons;

    public TopicFB() {
    }

    public TopicFB(int id, String name, String img, String description, List<LessonFB> lessons) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.description = description;
        this.lessons = lessons;
    }

    public TopicFB(Topic t) {
        this.id = t.getId();
        this.name = t.getName();
        this.img = t.getImg();
        this.description = t.getDescription();
        this.lessons = new ArrayList<>();
        for (Lesson l : t.getLessons()) {
            this.lessons.add(new LessonFB(l));
        }
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

    public String getDescription() {
        return description;
    }

    public List<LessonFB> getLessons() {
        return lessons;
    }
}
