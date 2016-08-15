package com.example.mypc.ezenglish.realm;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.Recod;
import com.example.mypc.ezenglish.model.Topic;

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

    public void addRecordbyid(Recod r, Lesson l) {
        realm.beginTransaction();
        Lesson lesson = getleassongbyid(l.getId());
        lesson.getRecods().add(r);
        realm.commitTransaction();

    }

    public void deleteRecordbyid(int i, Lesson l) {
        realm.beginTransaction();
        Lesson lesson = getleassongbyid(l.getId());
        lesson.getRecods().remove(i);
        realm.commitTransaction();

    }
}
