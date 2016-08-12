package com.example.mypc.ezenglish.util;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.activity.VocaActivity;
import com.example.mypc.ezenglish.adapter.VocaAdapter;
import com.example.mypc.ezenglish.receiver.AlarmBroadcastReceiver;

import java.text.SimpleDateFormat;

/**
 * Created by Quylt on 8/12/2016.
 */
public class AlarmUtil {

    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @TargetApi(Build.VERSION_CODES.N)
    public static void startAlarmvocar(int time, int id, Context context) {
        Calendar c = Calendar.getInstance();
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        intent.putExtra("id", id + "");
        PendingIntent pending = PendingIntent.getBroadcast(context.getApplicationContext(), id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long date = c.getTime().getTime() + time * 60 * 1000;
        Log.e("time", df.format(c.getTime()));
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
    public static void startNotification(int id, boolean sound, Context context) {
        Intent intent1 = new Intent(context, VocaActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bitmap x = UtilFunctions.getDefaultAlbumArt("/original/1/avatar.jpg");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(getNotificationIcon());
        builder.setLargeIcon(x);
        builder.setContentTitle("alarm");

        builder.setWhen(System.currentTimeMillis());
        builder.setTicker("den gio uong thuoc");
        if (sound == true) {
            builder.setContentText("noi dung co tteing");
        } else builder.setContentText("noi dung ko tteing");
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        notificationManager.notify(id, builder.build());
    }

    private static int getNotificationIcon() {
        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.ic_voca : R.drawable.ic_voca;
    }

}
