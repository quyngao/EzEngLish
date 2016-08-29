package com.example.mypc.ezenglish.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.datafirebase.UserFB;
import com.example.mypc.ezenglish.model.User;
import com.example.mypc.ezenglish.realm.RealmUser;
import com.example.mypc.ezenglish.util.Constant;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Quylt on 8/24/2016.
 */
public class SignupActivity extends Activity {
    private EditText inputEmail, inputPassword, inputName, inputDescription, inputBirthday;
    private CheckBox checkbokmale;
    UserFB user;

    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    final Firebase ref = new Firebase(Constant.FIREBASE_USER_URL);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputName = (EditText) findViewById(R.id.name);
        inputPassword = (EditText) findViewById(R.id.password);
        inputDescription = (EditText) findViewById(R.id.description);
        inputBirthday = (EditText) findViewById(R.id.birthday);
        user = new UserFB();
        checkbokmale = (CheckBox) findViewById(R.id.cb_male);
        checkbokmale.setChecked(true);
        user.setMale(true);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

        final Date t = Calendar.getInstance().getTime();
        inputBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog d = new DatePickerDialog(SignupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        inputBirthday.setText(i2 + "/" + i1 + "/" + i);
                    }
                }, t.getYear(), t.getMonth(), t.getDay());
                d.show();
            }
        });
        checkbokmale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                user.setMale(b);
                if (b) {
                    checkbokmale.setText("Boy");
                } else {
                    checkbokmale.setText("Girl");
                }
            }
        });


        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String name = inputPassword.getText().toString().trim();
                String des = inputDescription.getText().toString().trim();
                String birthday = inputBirthday.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(des)) {
                    Toast.makeText(getApplicationContext(), "Description", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(birthday)) {
                    Toast.makeText(getApplicationContext(), "Birthday", Toast.LENGTH_SHORT).show();
                    return;
                }

                user.setName(name);
                user.setBirthday(birthday);
                user.setEmail(email);
                user.setPassword(password);
                user.setDescription(des);

                progressBar.setVisibility(View.VISIBLE);
                ref.createUser(email, password, new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);

                        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                        builder.setMessage(R.string.signup_success)
                                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();

                        ref.authWithPassword(user.getEmail(), user.getPassword(), new Firebase.AuthResultHandler() {
                            @Override
                            public void onAuthenticated(AuthData authData) {
                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put("user", user);
                                ref.child(authData.getUid()).setValue(map);
                                User x = new User(user);
                                RealmUser realmUser = new RealmUser(SignupActivity.this);
                                realmUser.deleteUser();
                                realmUser.SaveUser(x);
                            }

                            @Override
                            public void onAuthenticationError(FirebaseError firebaseError) {

                            }
                        });
                    }


                    @Override
                    public void onError(FirebaseError firebaseError) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                        builder.setMessage(firebaseError.getMessage())
                                .setTitle(R.string.signup_error_title)
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
