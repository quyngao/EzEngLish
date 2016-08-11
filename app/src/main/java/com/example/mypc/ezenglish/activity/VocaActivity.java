package com.example.mypc.ezenglish.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.adapter.VocaAdapter;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.Voca;
import com.example.mypc.ezenglish.realm.RealmLeason;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voca);
        tts = new TextToSpeech(this, this);
        list = new ArrayList<>();
        listview = (ListView) findViewById(R.id.list);
        RealmLeason rl = new RealmLeason(this);
        Lesson l = rl.getleassongbyid(0);
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
