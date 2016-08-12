package com.example.mypc.ezenglish.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.adapter.VocaAdapter;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.Voca;
import com.example.mypc.ezenglish.realm.RealmLeason;
import com.example.mypc.ezenglish.util.AlarmUtil;
import com.example.mypc.ezenglish.util.PrefManager;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Quylt on 8/11/2016.
 */
public class VocaActivity extends Activity implements TextToSpeech.OnInitListener {

    ListView listview;
    VocaAdapter adapter;
    ArrayList<Voca> list;
    private TextToSpeech tts;
    SwitchCompat switchalarm;
    SwitchCompat switchsound;
    EditText tv_step;
    PrefManager prefManager;
    int step;
    boolean isalarm;
    boolean issound;
    Lesson l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voca);

        prefManager = new PrefManager(this);
        tv_step = (EditText) findViewById(R.id.text_time);
        switchalarm = (SwitchCompat) findViewById(R.id.switchalarm);
        switchsound = (SwitchCompat) findViewById(R.id.switchsound);

        step = prefManager.getstep();
        isalarm = prefManager.getsound();
        issound = prefManager.getalarm();

        tv_step.setText("" + step);
        switchalarm.setChecked(isalarm);
        switchsound.setChecked(issound);


        tts = new TextToSpeech(this, this);
        list = new ArrayList<>();
        listview = (ListView) findViewById(R.id.list);
        RealmLeason rl = new RealmLeason(this);
        l = rl.getleassongbyid(0);
        for (Voca vc : l.getVocas()) {
            list.add(vc);
        }
        Log.e("size", "" + list.size());
        adapter = new VocaAdapter(this, list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                speakOut(list.get(i).getName());
            }
        });


        switchsound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefManager.setsound(isChecked);
            }
        });
        switchsound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefManager.setalarm(isChecked);
                setupalarm(isChecked);
            }
        });
    }

    public void setupalarm(boolean ischeck) {
        if (ischeck == true) {
            Log.e("alarm", "start");
            step = Integer.parseInt(tv_step.getText().toString());
            prefManager.setstep(step);
            AlarmUtil.startAlarmvocar(step, l.getId(), this);
        } else {
            Log.e("alarm", "cancel");
            AlarmUtil.cancelAlarmvocar(l.getId(), this);
        }
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.ENGLISH);
            tts.setPitch((float) 1.2);
            tts.setSpeechRate((float) 0.8);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut("Hello Boy");
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    private void speakOut(String s) {
        tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

}
