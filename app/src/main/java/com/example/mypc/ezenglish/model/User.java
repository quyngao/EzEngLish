package com.example.mypc.ezenglish.model;

/**
 * Created by Quylt on 8/8/2016.
 */
public class User extends  RealmModel{

    String name;
    String email;
    String account;
    String password;
    String phone;
    String level;
    String birthday;
    String male;
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

    public void setLevel(String level) {
        this.level = level;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setMale(String male) {
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

    public String getLevel() {
        return level;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getMale() {
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
