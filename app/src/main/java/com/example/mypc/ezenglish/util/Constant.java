package com.example.mypc.ezenglish.util;

import java.text.SimpleDateFormat;

/**
 * Created by MyPC on 19/08/2016.
 */
public class Constant {
    public static String[] stateslesson = {"Complement", "Studying", "No learned"};
    public static String[] typemp3 = {"Audio", "Vocabulary", "Mini story"};
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat dfx = new SimpleDateFormat("yyyy-MM-dd");
    public static String FIREBASE_USER_URL = "https://ezfb.firebaseio.com/ezenglish/users";
    public static String FIREBASE_DATA_URL = "https://ezfb.firebaseio.com/ezenglish/data";
    public static String DATA_URL = "http://quyngao.16mb.com/ezenglish/";
}
