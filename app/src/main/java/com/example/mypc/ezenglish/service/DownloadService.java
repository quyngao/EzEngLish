package com.example.mypc.ezenglish.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MyPC on 28/08/2016.
 */
public class DownloadService extends IntentService {

    public static final int DOWNLOAD_ERROR = 10;
    public static final int DOWNLOAD_SUCCESS = 11;

    public DownloadService() {
        super(DownloadService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra("url");
        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        Bundle bundle = new Bundle();
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory().toString() + "/quyngao/");
        wallpaperDirectory.mkdirs();
        File downloadFile = new File(Environment.getExternalStorageDirectory().toString() + "/quyngao/abc.mp3");
        if (downloadFile.exists())
            downloadFile.delete();

        try {
            Log.e("ok", url);
            downloadFile.createNewFile();
            URL downloadURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) downloadURL
                    .openConnection();
            InputStream is = conn.getInputStream();
            FileOutputStream os = new FileOutputStream(downloadFile);
            byte buffer[] = new byte[1024];
            int byteCount;
            while ((byteCount = is.read(buffer)) != -1) {
                os.write(buffer, 0, byteCount);
            }
            os.close();
            is.close();

            String filePath = downloadFile.getPath();
            bundle.putString("filePath", filePath);
            receiver.send(DOWNLOAD_SUCCESS, bundle);

        } catch (Exception e) {
            receiver.send(DOWNLOAD_ERROR, Bundle.EMPTY);
            e.printStackTrace();
        }
    }
}
