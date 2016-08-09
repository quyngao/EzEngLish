package com.example.mypc.ezenglish.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class User extends RealmObject {
    @PrimaryKey
    public int id;
    String name;
    String email;
    String account;
    String password;
    String phone;
    int level;
    String birthday;
    int male;
    String img;
    int status;
    String description;
    String rate;

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getLevel() {
        return level;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getMale() {
        return male;
    }

    public String getImg() {
        return img;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getRate() {
        return rate;
    }
}
