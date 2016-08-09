package com.example.mypc.ezenglish.realm;

import android.app.Activity;

import com.example.mypc.ezenglish.model.Topic;

import io.realm.Realm;

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
}
