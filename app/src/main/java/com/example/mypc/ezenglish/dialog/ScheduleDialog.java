package com.example.mypc.ezenglish.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.model.Lesson;

/**
 * Created by MyPC on 02/09/2016.
 */
public class ScheduleDialog extends Dialog {
    Lesson l;
    Context mcontext;
    TextView tv_audio, tv_voca, tv_story;

    public ScheduleDialog(final Context mcontext, Lesson l) {
        super(mcontext);
        this.mcontext = mcontext;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(R.color.trongsuot);
        setContentView(R.layout.dialog_schedule);

        tv_audio = (TextView) findViewById(R.id.tv_audio);
        tv_voca = (TextView) findViewById(R.id.tv_voca);
        tv_story = (TextView) findViewById(R.id.tv_story);

        tv_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tpd = new TimePickerDialog(mcontext,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                String s = "";
                                if (hourOfDay < 10) s = s + "0" + hourOfDay + ":";
                                else s = s + "" + hourOfDay + ":";
                                if (minute < 10) s = s + "0" + minute;
                                else s = s + "" + minute;
                                tv_audio.setText(s);
                            }
                        }, 0, 0, true);
                tpd.show();
            }
        });
        tv_voca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tpd = new TimePickerDialog(mcontext,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                String s = "";
                                if (hourOfDay < 10) s = s + "0" + hourOfDay + ":";
                                else s = s + "" + hourOfDay + ":";
                                if (minute < 10) s = s + "0" + minute;
                                else s = s + "" + minute;
                                tv_voca.setText(s);
                            }
                        }, 0, 0, true);
                tpd.show();
            }
        });
        tv_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tpd = new TimePickerDialog(mcontext,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                String s = "";
                                if (hourOfDay < 10) s = s + "0" + hourOfDay + ":";
                                else s = s + "" + hourOfDay + ":";
                                if (minute < 10) s = s + "0" + minute;
                                else s = s + "" + minute;
                                tv_story.setText(s);
                            }
                        }, 0, 0, true);
                tpd.show();
            }
        });
    }
}
