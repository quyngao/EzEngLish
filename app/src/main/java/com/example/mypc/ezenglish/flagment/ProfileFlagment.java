package com.example.mypc.ezenglish.flagment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.activity.HomeActivity;
import com.example.mypc.ezenglish.util.Constant;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.special.ResideMenu.ResideMenu;

import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;

/**
 * Created by MyPC on 28/08/2016.
 */
public class ProfileFlagment extends Fragment {
    private View parentView;
    private ResideMenu resideMenu;

    private Button btnChangeEmail, btnChangePassword, btnSendResetEmail,
            changeEmail, changePassword;

    final Firebase ref = new Firebase(Constant.FIREBASE_USER_URL);
    private EditText oldEmail, newEmail, password, newPassword;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_profile, container, false);
        setUpViews();
        return parentView;
    }

    private void setUpViews() {
        HomeActivity parentActivity = (HomeActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();

        btnChangeEmail = (Button) parentView.findViewById(R.id.change_email_button);
        btnChangePassword = (Button) parentView.findViewById(R.id.change_password_button);
        changeEmail = (Button) parentView.findViewById(R.id.changeEmail);
        changePassword = (Button) parentView.findViewById(R.id.changePass);
        btnSendResetEmail = (Button) parentView.findViewById(R.id.send);

        oldEmail = (EditText) parentView.findViewById(R.id.old_email);
        newEmail = (EditText) parentView.findViewById(R.id.new_email);
        password = (EditText) parentView.findViewById(R.id.password);
        newPassword = (EditText) parentView.findViewById(R.id.newPassword);

        oldEmail.setVisibility(View.GONE);
        newEmail.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        newPassword.setVisibility(View.GONE);
        changeEmail.setVisibility(View.GONE);
        changePassword.setVisibility(View.GONE);

        progressBar = (ProgressBar) parentView.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);
        setgraph();
        setListener();
    }

    public void setgraph() {
        StackedBarChart mStackedBarChart = (StackedBarChart) parentView.findViewById(R.id.stackedbarchart);

        StackedBarModel s1 = new StackedBarModel("Lesson 1");

        s1.addBar(new BarModel(2f, getResources().getColor(R.color.color_audio)));
        s1.addBar(new BarModel(4f, getResources().getColor(R.color.color_voca)));
        s1.addBar(new BarModel(4f, getResources().getColor(R.color.color_mini)));

        StackedBarModel s2 = new StackedBarModel("Lesson 2");
        s2.addBar(new BarModel(1.2f, getResources().getColor(R.color.color_audio)));
        s2.addBar(new BarModel(3f, getResources().getColor(R.color.color_voca)));
        s2.addBar(new BarModel(5f, getResources().getColor(R.color.color_mini)));

        StackedBarModel s3 = new StackedBarModel("Lessson 3");

        s3.addBar(new BarModel(2.1f, getResources().getColor(R.color.color_audio)));
        s3.addBar(new BarModel(2f, getResources().getColor(R.color.color_voca)));
        s3.addBar(new BarModel(6f, getResources().getColor(R.color.color_mini)));

        StackedBarModel s4 = new StackedBarModel("Lessson 4");
        s4.addBar(new BarModel(2.1f, getResources().getColor(R.color.color_audio)));
        s4.addBar(new BarModel(2f, getResources().getColor(R.color.color_voca)));
        s4.addBar(new BarModel(6f, getResources().getColor(R.color.color_mini)));


        mStackedBarChart.addBar(s1);
        mStackedBarChart.addBar(s2);
        mStackedBarChart.addBar(s3);
        mStackedBarChart.addBar(s4);

        mStackedBarChart.startAnimation();
    }

    public void setListener() {
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldEmail.setVisibility(View.GONE);
                newEmail.setVisibility(View.GONE);
                password.setVisibility(View.GONE);
                newPassword.setVisibility(View.VISIBLE);
                changeEmail.setVisibility(View.GONE);
                changePassword.setVisibility(View.VISIBLE);

            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
//                if (user != null && !newPassword.getText().toString().trim().equals("")) {
//                    if (newPassword.getText().toString().trim().length() < 6) {
//                        newPassword.setError("Password too short, enter minimum 6 characters");
//                        progressBar.setVisibility(View.GONE);
//                    } else {
//                        user.updatePassword(newPassword.getText().toString().trim())
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            Toast.makeText(MainActivity.this, "Password is updated, sign in with new password!", Toast.LENGTH_SHORT).show();
//                                            signOut();
//                                            progressBar.setVisibility(View.GONE);
//                                        } else {
//                                            Toast.makeText(MainActivity.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
//                                            progressBar.setVisibility(View.GONE);
//                                        }
//                                    }
//                                });
//                    }
//                } else if (newPassword.getText().toString().trim().equals("")) {
//                    newPassword.setError("Enter password");
//                    progressBar.setVisibility(View.GONE);
//                }
            }
        });
        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (!newEmail.getText().toString().trim().equals("")) {
//                    user.updateEmail(newEmail.getText().toString().trim())
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(MainActivity.this, "Email address is updated. Please sign in with new email id!", Toast.LENGTH_LONG).show();
//                                        signOut();
//                                        progressBar.setVisibility(View.GONE);
//                                    } else {
//                                        Toast.makeText(MainActivity.this, "Failed to update email!", Toast.LENGTH_LONG).show();
//                                        progressBar.setVisibility(View.GONE);
//                                    }
//                                }
//                            });

                    ref.changeEmail("quyltptit@gmail.com", "1", "newemail@firebase.com", new Firebase.ResultHandler() {
                        @Override
                        public void onSuccess() {
                            // email changed
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            // error encountered
                        }
                    });
                } else if (newEmail.getText().toString().trim().equals("")) {
                    newEmail.setError("Enter email");
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
