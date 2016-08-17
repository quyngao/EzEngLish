package com.example.mypc.ezenglish.realm;

import android.app.Activity;

import com.example.mypc.ezenglish.model.Topic;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by MyPC on 09/08/2016.
 */
public class RealmTopic {
    Realm realm;

    public RealmTopic(Activity context) {
        realm = RealmController.with(context).getRealm();
    }

    public void Savetopic(Topic t) {
        realm.beginTransaction();
        realm.copyToRealm(t);
        realm.commitTransaction();
    }

    public Topic getTopicbyid(int id) {
        return realm.where(Topic.class).equalTo("id", id).findFirst();
    }

    public ArrayList<Topic> getAllTopic() {
        ArrayList<Topic> list = new ArrayList<>();
        for (Topic i : realm.where(Topic.class).findAll()) {
            list.add(i);
        }
        return list;
    }
}
