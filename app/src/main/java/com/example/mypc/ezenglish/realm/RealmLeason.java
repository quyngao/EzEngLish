package com.example.mypc.ezenglish.realm;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.mypc.ezenglish.model.History;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.Recod;
import com.example.mypc.ezenglish.model.Topic;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by MyPC on 09/08/2016.
 */
public class RealmLeason {
    Realm realm;

    public RealmLeason(Activity context) {
        realm = RealmController.with(context).getRealm();
    }

    public RealmLeason(Context context) {
        realm = RealmController.with(context).getRealm();

    }

    public RealmLeason(Application context) {
        realm = RealmController.with(context).getRealm();
    }

    public void Savetopic(Lesson l) {
        realm.beginTransaction();
        realm.copyToRealm(l);
        realm.commitTransaction();
    }

    public RealmList<Lesson> getTopicbyid(int id) {
        Topic t = realm.where(Topic.class).equalTo("id", id).findFirst();
        return t.getLessons();
    }

    public Lesson getleassongbyid(int id) {
        Lesson l = realm.where(Lesson.class).equalTo("id", id).findFirst();
        return l;
    }

    public void addRecordbyid(Recod r, int idl) {
        realm.beginTransaction();
        Lesson lesson = getleassongbyid(idl);
        int size = lesson.getRecods().size();

        lesson.getRecods().add(r);
        realm.commitTransaction();
    }

    public void addHistorybyid(History h, int idl) {
        realm.beginTransaction();
        Lesson lesson = getleassongbyid(idl);
        lesson.getHistories().add(h);
        realm.commitTransaction();
    }

    public void saveLocal(Lesson l) {
        realm.beginTransaction();
        Lesson lesson = getleassongbyid(l.getId());
        lesson.setIsrealy(1);
        for (int i = 0; i < l.getAll().size(); i++) {
            l.getAll().get(i).setLocation("/original/" + l.getId() + "/" + l.getAll().get(i).getName() + ".mp3");
        }
        realm.commitTransaction();
    }


    public ArrayList<Lesson> getAllLeasson() {
        ArrayList<Lesson> list = new ArrayList<>();
        for (Lesson i : realm.where(Lesson.class).findAll()) {
            list.add(i);
        }
        return list;
    }

    public void deleteRecordbyid(int i, Lesson l) {
        realm.beginTransaction();
        Lesson lesson = getleassongbyid(l.getId());
        lesson.getRecods().remove(i);
        realm.commitTransaction();
    }
}
