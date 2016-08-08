package com.example.mypc.ezenglish.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by MyPC on 08/08/2016.
 */
public class RealmModel  extends RealmObject {
    @PrimaryKey
    public int id;
}
