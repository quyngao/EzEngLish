package com.example.mypc.ezenglish.receiver;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.model.Alarm;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.Voca;
import com.example.mypc.ezenglish.model.VocaRed;
import com.example.mypc.ezenglish.realm.RealmLeason;
import com.example.mypc.ezenglish.util.AlarmUtil;
import com.example.mypc.ezenglish.util.PrefManager;

import java.util.List;
import java.util.Random;

import io.realm.RealmList;

/**
 * Created by Quylt on 8/12/2016.
 */
public class AlarmBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("alarm", "da vao day");
        String intentId = intent.getStringExtra("id");
        int id = Integer.parseInt(intentId);
        PrefManager prefManager = new PrefManager(context);
        RealmLeason rl = new RealmLeason(context);
        RealmList<Voca> list = rl.getleassongbyid(id).getVocas();
        VocaRed vcr = prefManager.getVocaRed();
        int time = vcr.getStep();
        boolean sound = vcr.issound();
        boolean alarm = vcr.isalarm();
        Log.e("size", "" + list.size());
        if (alarm == false) {
            AlarmUtil.cancelAlarmvocar(id, context);
        } else {
            Random r = new Random();
            Voca vc = list.get(r.nextInt(list.size()));
            AlarmUtil.startNotification(vc, id, sound, context);
            AlarmUtil.startAlarmvocar(time, id, context);
        }
    }
}
