package com.example.mypc.ezenglish.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.datafirebase.TopicFB;
import com.example.mypc.ezenglish.model.Topic;
import com.example.mypc.ezenglish.realm.DataDummyLocal;
import com.example.mypc.ezenglish.realm.RealmTopic;
import com.example.mypc.ezenglish.util.Constant;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.core.operation.AckUserWrite;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Quylt on 8/24/2016.
 */
public class LoginActivity extends Activity {
    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    final Firebase ref = new Firebase(Constant.FIREBASE_USER_URL);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ref.getAuth() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();


                if (email.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage(R.string.login_error_message)
                            .setTitle("Error!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    final String emailAddress = email;
                    ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            progressBar.setVisibility(View.GONE);
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("email", emailAddress);
                            ref.child("ezer").child(authData.getUid()).updateChildren(map);

                            setdatadummy();

                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage(firebaseError.getMessage())
                                    .setTitle("Error!")
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
                }
            }
        });
    }

    public void setdatadummy() {

//        DataDummyLocal d = new DataDummyLocal();
//        Topic t = d.saveTopic();


        Firebase rootRef = new Firebase(Constant.FIREBASE_DATA_URL);
        Firebase alanRef = rootRef.child("topic");
        alanRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                TopicFB x = snapshot.getValue(TopicFB.class);
                Topic t = new Topic(x);
                RealmTopic r = new RealmTopic(LoginActivity.this);
                r.Savetopic(t);
                Topic tsave = r.getTopicbyid(1);
                DataDummyLocal d = new DataDummyLocal();
//                r.Savetopic(d.saveTopic());
                d.showTopic(tsave);

                Intent intent = new Intent(LoginActivity.this, LessonActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
