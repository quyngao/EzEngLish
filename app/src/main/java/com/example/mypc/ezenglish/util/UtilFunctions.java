package com.example.mypc.ezenglish.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Application;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.MP3;
import com.example.mypc.ezenglish.realm.RealmLeason;

import java.io.FileDescriptor;
import java.util.ArrayList;

public class UtilFunctions {
    static String LOG_CLASS = "UtilFunctions";


    public static boolean isServiceRunning(String serviceName, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceName.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<MP3> listOfSongs(Application activity) {
        RealmLeason rl = new RealmLeason(activity);
        Lesson l = rl.getleassongbyid(0);
        ArrayList<MP3> list = new ArrayList<>();
        for (MP3 i : l.getAll()) {
            list.add(i);
        }
        return list;
//		Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//		Cursor c = context.getContentResolver().query(uri, null, MediaStore.Audio.Media.IS_MUSIC + " != 0", null, null);
//		ArrayList<MediaItem> listOfSongs = new ArrayList<MediaItem>();
//		c.moveToFirst();
//		while(c.moveToNext()){
//			MediaItem songData = new MediaItem();
//
//			String title = c.getString(c.getColumnIndex(MediaStore.Audio.Media.TITLE));
//			String artist = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ARTIST));
//			String album = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ALBUM));
//			long duration = c.getLong(c.getColumnIndex(MediaStore.Audio.Media.DURATION));
//			String data = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));
//			long albumId = c.getLong(c.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
//			String composer = c.getString(c.getColumnIndex(MediaStore.Audio.Media.COMPOSER));
//
//			songData.setTitle(title);
//			songData.setAlbum(album);
//			songData.setArtist(artist);
//			songData.setDuration(duration);
//			songData.setPath(data);
//			songData.setAlbumId(albumId);
//			songData.setComposer(composer);
//			listOfSongs.add(songData);
//		}
//		c.close();
//		Log.d("SIZE", "SIZE: " + listOfSongs.size());
//		return listOfSongs;
    }

    public static Bitmap getDefaultAlbumArt(String location) {
        Bitmap bm = null;
        try {
            bm = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + location);
        } catch (Error ee) {
        } catch (Exception e) {
        }
        return bm;
    }
    private static final float BITMAP_SCALE = 1f;
    private static final float BLUR_RADIUS = 6.5f;
    public static Bitmap blur(String location,Context context) {
        Bitmap image =getDefaultAlbumArt(location);
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }

    public static String getDuration(long milliseconds) {
        long sec = (milliseconds / 1000) % 60;
        long min = (milliseconds / (60 * 1000)) % 60;
        long hour = milliseconds / (60 * 60 * 1000);

        String s = (sec < 10) ? "0" + sec : "" + sec;
        String m = (min < 10) ? "0" + min : "" + min;
        String h = "" + hour;

        String time = "";
        if (hour > 0) {
            time = h + ":" + m + ":" + s;
        } else {
            time = m + ":" + s;
        }
        return time;
    }

    public static boolean currentVersionSupportBigNotification() {
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        if (sdkVersion >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            return true;
        }
        return false;
    }

    public static boolean currentVersionSupportLockScreenControls() {
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        if (sdkVersion >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return true;
        }
        return false;
    }
}
