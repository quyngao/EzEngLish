package com.example.mypc.ezenglish.realm;

import android.app.Activity;

import com.example.mypc.ezenglish.model.User;

import io.realm.Realm;

/**
 * Created by MyPC on 09/08/2016.
 */
public class RealmUser {
    Realm realm;

    public RealmUser(Activity context) {
        realm = RealmController.with(context).getRealm();
    }

    public void SaveUser(User user) {
        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();
    }

    public void EditUser(User user) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();

    }

    public User getUser(String account, String pass) {
        return realm.where(User.class).equalTo("account", account).equalTo("password", pass).findFirst();
    }
}
