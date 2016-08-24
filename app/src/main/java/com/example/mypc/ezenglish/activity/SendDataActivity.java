package com.example.mypc.ezenglish.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.os.PersistableBundle;
import android.util.Log;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.datafirebase.TopicFB;
import com.example.mypc.ezenglish.datafirebase.UserFB;
import com.example.mypc.ezenglish.model.Topic;
import com.example.mypc.ezenglish.model.User;
import com.example.mypc.ezenglish.realm.DataDummyLocal;
import com.example.mypc.ezenglish.realm.RealmTopic;
import com.example.mypc.ezenglish.realm.RealmUser;
import com.example.mypc.ezenglish.util.Constant;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Quylt on 8/24/2016.
 */
public class SendDataActivity extends Activity {
    Firebase rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootRef = new Firebase(Constant.FIREBASE_DATA_URL);
        setdatadummy();
    }

    public void setdatadummy() {
        Log.e("ok", "da vao day roi");
//        User x = new User();
//        x.setId(1);
//        x.setAccount("quyngao");
//        x.setBirthday("16/03/1995");
//        x.setDescription("dep zai");
//        x.setEmail("quy@gmail.com");
//        x.setImg("ezapp/img");
//        x.setLevel(0);
//        x.setMale(0);
//        x.setName("quy ngao");
//        x.setPassword("quydz");
//        x.setPhone("012234");
//        x.setRate("100");
//        x.setStatus(0);
//
//        UserFB xfb = new UserFB(x);


//        Firebase alanRef = rootRef.child("users");
//        alanRef.setValue(xfb, new Firebase.CompletionListener() {
//            @Override
//            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
//                Log.e("ok", "done");
//            }
//        });


//        alanRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
////                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
////                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
////                    Post post = postSnapshot.getValue(Post.class);
////                    System.out.println(post.getAuthor() + " - " + post.getTitle());
////                }
//                UserFB x = snapshot.getValue(UserFB.class);
//                System.out.println("There are " + x.getName());
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                System.out.println("The read failed: " + firebaseError.getMessage());
//            }
//        });

//
//        RealmUser realmUser = new RealmUser(this);
//        realmUser.SaveUser(x);
//        User y = realmUser.getUser("quyngao", "quydz");
//        Log.e("user", y.getName());
        DataDummyLocal d = new DataDummyLocal();
        Topic t = d.saveTopic();

        TopicFB tf = new TopicFB(t);

        Firebase alanRef = rootRef.child("topic");
//        alanRef.up(tf, new Firebase.CompletionListener() {
//            @Override
//            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
//                Log.e("ok", "done");
//            }
//        });

        alanRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
//                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
//                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                    Post post = postSnapshot.getValue(Post.class);
//                    System.out.println(post.getAuthor() + " - " + post.getTitle());
//                }
                TopicFB x = snapshot.getValue(TopicFB.class);
                System.out.println("There are " + x.getName());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        RealmTopic r = new RealmTopic(this);
        r.Savetopic(t);
        Topic tsave = r.getTopicbyid(1);
        d.showTopic(tsave);
    }
}
