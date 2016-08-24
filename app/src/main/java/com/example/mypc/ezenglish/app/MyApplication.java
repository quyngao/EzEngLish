package com.example.mypc.ezenglish.app;

import android.app.Application;

import com.firebase.client.Firebase;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by MyPC on 08/08/2016.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        Firebase.setAndroidContext(this);

    }
}
