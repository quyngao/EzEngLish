package com.example.mypc.ezenglish.datafirebase;

import com.example.mypc.ezenglish.model.User;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quylt on 8/8/2016.
 */
public class UserFB {

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

    public UserFB() {
    }

    public UserFB(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.email = u.getEmail();
        this.account = u.getImg();
        this.password = u.getPassword();
        this.phone = u.getPhone();
        this.level = u.getLevel();
        this.birthday = u.getBirthday();
        this.male = u.getMale();
        this.img = u.getImg();
        this.status = u.getStatus();
        this.description = u.getDescription();
        this.rate = u.getRate();
    }

    public UserFB(int id, String name, String email, String account, String password, String phone, int level, String birthday, int male, String img, int status, String description, String rate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.level = level;
        this.birthday = birthday;
        this.male = male;
        this.img = img;
        this.status = status;
        this.description = description;
        this.rate = rate;
    }

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
