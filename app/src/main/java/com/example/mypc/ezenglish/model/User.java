package com.example.mypc.ezenglish.model;

import com.example.mypc.ezenglish.datafirebase.UserFB;

import io.realm.RealmObject;

/**
 * Created by Quylt on 8/8/2016.
 */
public class User extends RealmObject {

    String name;
    String email;
    String password;
    String birthday;
    boolean male;
    String description;

    public User() {
    }

    public User(UserFB u) {
        this.name = u.getName();
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.birthday = u.getBirthday();
        this.male = u.isMale();
        this.description = u.getDescription();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }

    public boolean isMale() {
        return male;
    }

    public String getDescription() {
        return description;
    }
}
