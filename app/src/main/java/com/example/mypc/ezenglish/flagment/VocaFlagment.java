package com.example.mypc.ezenglish.flagment;


import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.adapter.VocaAdapter;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.Voca;
import com.example.mypc.ezenglish.model.VocaRed;
import com.example.mypc.ezenglish.realm.RealmLeason;
import com.example.mypc.ezenglish.util.AlarmUtil;
import com.example.mypc.ezenglish.util.PrefManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Quylt on 8/18/2016.
 */
public class VocaFlagment extends Fragment implements TextToSpeech.OnInitListener {

    Context context;
    ListView listview;
    VocaAdapter adapter;
    List<Voca> list;
    private TextToSpeech tts;
    SwitchCompat switchalarm;
    SwitchCompat switchsound;
//    EditText tv_step;
    TextView tv_start, tv_end;
    PrefManager prefManager;
    VocaRed vcr;
    Lesson l;

    public static VocaFlagment newInstance() {
        VocaFlagment fragmentFirst = new VocaFlagment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
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

    @Override
    public void onPause() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_voca, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        init();
    }

    public void init() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        prefManager = new PrefManager(context);
//        tv_step = (EditText) getActivity().findViewById(R.id.text_time);
        switchalarm = (SwitchCompat) getActivity().findViewById(R.id.switchalarm);
        switchsound = (SwitchCompat) getActivity().findViewById(R.id.switchsound);
        tv_start = (TextView) getActivity().findViewById(R.id.tv_start);
        tv_end = (TextView) getActivity().findViewById(R.id.tv_end);
        vcr = prefManager.getVocaRed();

//        tv_step.setText("" + vcr.getStep());
        switchalarm.setChecked(vcr.isalarm());
        switchsound.setChecked(vcr.issound());
        tv_start.setText(vcr.getStartdate());
        tv_end.setText(vcr.getEnddate());


        tts = new TextToSpeech(context, this);
        list = new ArrayList<>();
        listview = (ListView) getActivity().findViewById(R.id.list);
        RealmLeason rl = new RealmLeason(context);
        l = rl.getleassongbyid(0);
        for (Voca vc : l.getVocas()) {
            list.add(vc);
        }

        adapter = new VocaAdapter(context, list);
        listview.setAdapter(adapter);


        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog tpd = new TimePickerDialog(context,
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
                TimePickerDialog tpd = new TimePickerDialog(context,
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
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                speakOut(list.get(i).getName());
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

    private void speakOut(String s) {
        tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void setupalarm(boolean ischeck) {
        if (ischeck == true) {
            Log.e("alarm", "start");
//            vcr.setStep(Integer.parseInt(tv_step.getText().toString()));
            prefManager.setVocared(vcr);
            List<Voca> listtmp = prefManager.getlistvoca();
            if (listtmp.size() == 0) {
                Log.e("size", "" + list.size());
//                prefManager.setlistvoca(list);
            }
            AlarmUtil.startAlarmvocar(vcr.getStep(), l.getId(), context);
        } else {
            Log.e("alarm", "cancel");
            AlarmUtil.cancelAlarmvocar(l.getId(), context);
        }
    }

}
