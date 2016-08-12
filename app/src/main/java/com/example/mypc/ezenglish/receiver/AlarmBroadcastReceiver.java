package com.example.mypc.ezenglish.receiver;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.example.mypc.ezenglish.model.Alarm;
import com.example.mypc.ezenglish.util.AlarmUtil;
import com.example.mypc.ezenglish.util.PrefManager;

/**
 * Created by Quylt on 8/12/2016.
 */
public class AlarmBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("alarm","da vao day");
        String intentId = intent.getStringExtra("id");

        int id = Integer.parseInt(intentId);
        PrefManager prefManager = new PrefManager(context);
        int time = prefManager.getstep();
        boolean sound = prefManager.getsound();
        boolean alarm = prefManager.getalarm();
        if (alarm == false) {
            AlarmUtil.cancelAlarmvocar(id, context);
        } else {
            AlarmUtil.startNotification(id, sound, context);
            AlarmUtil.startAlarmvocar(time, id, context);
        }
    }
}
