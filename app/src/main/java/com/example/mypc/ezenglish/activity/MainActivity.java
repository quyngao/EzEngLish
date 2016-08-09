package com.example.mypc.ezenglish.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.model.User;
import com.example.mypc.ezenglish.realm.RealmUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User x = new User();
        x.setId(1);
        x.setAccount("quyngao");
        x.setBirthday("16/03/1995");
        x.setDescription("dep zai");
        x.setEmail("quy@gmail.com");
        x.setImg("ezapp/img");
        x.setLevel(0);
        x.setMale(0);
        x.setName("quy ngao");
        x.setPassword("quydz");
        x.setPhone("012234");
        x.setRate("100");
        x.setStatus(0);

        RealmUser realmUser = new RealmUser(this);
        realmUser.SaveUser(x);
        User y = realmUser.getUser("quyngao","quydz");

        Log.e("user",y.getName());
    }
}
