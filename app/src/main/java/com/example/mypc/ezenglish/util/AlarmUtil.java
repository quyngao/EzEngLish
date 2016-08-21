package com.example.mypc.ezenglish.util;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.activity.VocaActivity;
import com.example.mypc.ezenglish.model.VocaData;
import com.example.mypc.ezenglish.receiver.AlarmBroadcastReceiver;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Quylt on 8/12/2016.
 */
public class AlarmUtil {

    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void startAlarmvocar(int time, int id, Context context) {

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        intent.putExtra("id", id + "");
        PendingIntent pending = PendingIntent.getBroadcast(context.getApplicationContext(), id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Date d = new Date();
        long date = d.getTime() + time * 60 * 1000;
        Log.e("time", df.format(d));
        if (Build.VERSION.SDK_INT >= 19) {
            am.setExact(AlarmManager.RTC_WAKEUP, date, pending);
        } else am.set(AlarmManager.RTC_WAKEUP, date, pending);
    }

    public static void cancelAlarmvocar(int id, Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        intent.putExtra("id", id + "");
        PendingIntent pending = PendingIntent.getBroadcast(context.getApplicationContext(), id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        am.cancel(pending);
    }


    private static void speakOut(String s, TextToSpeech tt) {
        tt.speak(s, TextToSpeech.QUEUE_FLUSH, null);
    }
    public static void buildNotificaiton(VocaData voca, int id,int idvoca, boolean sound, Context context) {


        Intent intent1 = new Intent(context, VocaActivity.class);
        intent1.putExtra("id",idvoca);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        RemoteViews simpleContentView = new RemoteViews(context.getApplicationContext().getPackageName(), R.layout.notification_voca);
        Notification notification = new NotificationCompat.Builder(context.getApplicationContext())
                .setSmallIcon(R.drawable.ic_music).setAutoCancel(true)
                .setContentTitle(voca.getName()).build();
        notification.contentView = simpleContentView;
        notification.contentView.setImageViewBitmap(R.id.image, UtilFunctions.getDefaultAlbumArt(voca.getImg()));
        notification.contentView.setTextViewText(R.id.text, voca.getName());
        notification.contentView.setTextViewText(R.id.description, voca.getDescription());
        notification.when = System.currentTimeMillis();
        notification.contentIntent = pendingIntent;

        notificationManager.notify(id, notification);

    }


}
