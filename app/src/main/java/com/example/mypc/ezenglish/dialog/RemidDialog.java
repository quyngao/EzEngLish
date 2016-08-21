package com.example.mypc.ezenglish.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.VocaRed;
import com.example.mypc.ezenglish.util.AlarmUtil;
import com.example.mypc.ezenglish.util.PrefManager;

/**
 * Created by MyPC on 21/08/2016.
 */
public class RemidDialog extends Dialog {
    Context mcontext;
    SwitchCompat switchalarm;
    SwitchCompat switchsound;
    TextView tv_start, tv_end;
    EditText tv_step;
    VocaRed vcr;
    PrefManager prefManager;
    Lesson l;

    public RemidDialog(final Context mcontext, Lesson l) {
        super(mcontext);
        this.mcontext = mcontext;
        prefManager = new PrefManager(mcontext);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(R.color.trongsuot);
        setContentView(R.layout.dialog_settingremind);
        switchalarm = (SwitchCompat) findViewById(R.id.switchalarm);
        switchsound = (SwitchCompat) findViewById(R.id.switchsound);
        tv_start = (TextView) findViewById(R.id.tv_start);
        tv_end = (TextView) findViewById(R.id.tv_end);
        tv_step = (EditText) findViewById(R.id.text_step);

        vcr = prefManager.getVocaRed();
        this.l = l;
        tv_step.setText("" + vcr.getStep());
        switchalarm.setChecked(vcr.isalarm());
        switchsound.setChecked(vcr.issound());
        tv_start.setText(vcr.getStartdate());
        tv_end.setText(vcr.getEnddate());
        tv_start.setOnClickListener(new View.OnClickListener() {
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
                                tv_start.setText(s);
                                vcr.setStartdate(s);
                            }
                        }, 0, 0, true);
                tpd.show();
            }
        });
        tv_end.setOnClickListener(new View.OnClickListener() {
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
                                tv_end.setText(s);
                                vcr.setEnddate(s);
                            }
                        }, 0, 0, true);
                tpd.show();
            }
        });
        switchsound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("sound", "sound");
                vcr.setIssound(isChecked);
                prefManager.setVocared(vcr);
            }
        });
        switchalarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                vcr.setIsalarm(isChecked);
                prefManager.setVocared(vcr);
                setupalarm(isChecked);
            }
        });

    }

    public void setupalarm(boolean ischeck) {
        if (ischeck == true) {
            Log.e("alarm", "start");
            vcr.setStep(Integer.parseInt(tv_step.getText().toString()));
            prefManager.setVocared(vcr);
            AlarmUtil.startAlarmvocar(vcr.getStep(), l.getId(), mcontext);
        } else {
            Log.e("alarm", "cancel");
            AlarmUtil.cancelAlarmvocar(l.getId(), mcontext);
        }
    }
}
