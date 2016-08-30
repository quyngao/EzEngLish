package com.example.mypc.ezenglish.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.MetadataEditor;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.cleveroad.audiowidget.AudioWidget;
import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.activity.ItemLessonActivity;
import com.example.mypc.ezenglish.activity.LessonActivity;
import com.example.mypc.ezenglish.controls.Controls;
import com.example.mypc.ezenglish.model.History;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.MP3;
import com.example.mypc.ezenglish.realm.RealmLeason;
import com.example.mypc.ezenglish.receiver.NotificationBroadcast;
import com.example.mypc.ezenglish.util.Constant;
import com.example.mypc.ezenglish.util.PlayerConstants;
import com.example.mypc.ezenglish.util.PrefManager;
import com.example.mypc.ezenglish.util.UtilFunctions;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static com.cleveroad.audiowidget.AudioWidget.OnControlsClickListener;
import static com.cleveroad.audiowidget.AudioWidget.OnWidgetStateChangedListener;
import static com.cleveroad.audiowidget.AudioWidget.State;

public class SongService extends Service implements AudioManager.OnAudioFocusChangeListener {
    String LOG_CLASS = "SongService";
    private MediaPlayer mp;
    AudioWidget audioWidget;
    int NOTIFICATION_ID = 1111;
    public static final String NOTIFY_PREVIOUS = "com.example.mypc.ezenglish.previous";
    public static final String NOTIFY_DELETE = "com.example.mypc.ezenglish.delete";
    public static final String NOTIFY_PAUSE = "com.example.mypc.ezenglish.pause";
    public static final String NOTIFY_PLAY = "com.example.mypc.ezenglish.play";
    public static final String NOTIFY_NEXT = "com.example.mypc.ezenglish.next";
    private ComponentName remoteComponentName;
    private RemoteControlClient remoteControlClient;
    AudioManager audioManager;
    Bitmap mDummyAlbumArt;
    private static Timer timer;
    private static boolean currentVersionSupportBigNotification = false;
    private static boolean currentVersionSupportLockScreenControls = false;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mp = new MediaPlayer();
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        currentVersionSupportBigNotification = UtilFunctions.currentVersionSupportBigNotification();
        currentVersionSupportLockScreenControls = UtilFunctions.currentVersionSupportLockScreenControls();
        timer = new Timer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
            audioWidget = new AudioWidget.Builder(getApplicationContext()).lightColor(getApplication().getResources().getColor(R.color.colorPrimary))
                    .darkColor(getApplication().getResources().getColor(R.color.colorPrimaryDark))
                    .expandWidgetColor(getApplication().getResources().getColor(R.color.colorPrimary))
                    .progressColor(getApplication().getResources().getColor(R.color.colorAccent))
                    .progressStrokeWidth(5.f)
                    .crossColor(getApplication().getResources().getColor(R.color.colorAccent))
                    .crossOverlappedColor(getApplication().getResources().getColor(R.color.colorPrimaryDark))
                    .crossStrokeWidth(5.f).defaultAlbumDrawable(getApplication().getResources().getDrawable(R.drawable.ic_music))
                    .shadowColor(getApplication().getResources().getColor(R.color.colorPrimaryDark))
                    .build();
        }
        mp.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Controls.nextControl(getApplicationContext());
            }
        });
        super.onCreate();
    }

    /**
     * Send message from timer
     *
     * @author jonty.ankit
     */
    private class MainTask extends TimerTask {
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mp != null) {
                int progress = (mp.getCurrentPosition() * 100) / mp.getDuration();
                Integer i[] = new Integer[3];
                i[0] = mp.getCurrentPosition();
                i[1] = mp.getDuration();
                i[2] = progress;
                try {
                    PlayerConstants.PROGRESSBAR_HANDLER.sendMessage(PlayerConstants.PROGRESSBAR_HANDLER.obtainMessage(0, i));
                } catch (Exception e) {
                }
            }
        }
    };

    @SuppressLint("NewApi")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            if (PlayerConstants.SONGS_LIST.size() <= 0) {
                PlayerConstants.SONGS_LIST = UtilFunctions.listOfSongs(getApplication(), PlayerConstants.ID_LEASSON);
            }
            MP3 data = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER);
            if (currentVersionSupportLockScreenControls) {
                RegisterRemoteClient();
            }
            String songPath = data.getLocation();
            playSong(songPath, data);
            newNotification();
            PlayerConstants.SONG_CHANGE_HANDLER = new Handler(new Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    MP3 data = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER);
                    String songPath = data.getLocation();
                    newNotification();
                    try {
                        saveHistory();
                        playSong(songPath, data);
                        LessonActivity.changeUI();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            });

            PlayerConstants.PLAY_PAUSE_HANDLER = new Handler(new Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    String message = (String) msg.obj;
                    if (mp == null)
                        return false;
                    if (message.equalsIgnoreCase(getResources().getString(R.string.play))) {
                        PlayerConstants.SONG_PAUSED = false;
                        if (currentVersionSupportLockScreenControls) {
                            remoteControlClient.setPlaybackState(RemoteControlClient.PLAYSTATE_PLAYING);
                        }
                        mp.start();
                        audioWidget.controller().start();
                    } else if (message.equalsIgnoreCase(getResources().getString(R.string.pause))) {
                        PlayerConstants.SONG_PAUSED = true;
                        if (currentVersionSupportLockScreenControls) {
                            remoteControlClient.setPlaybackState(RemoteControlClient.PLAYSTATE_PAUSED);
                        }
                        mp.pause();
                        audioWidget.controller().pause();
                    }
                    newNotification();
                    try {
                        LessonActivity.changeButton();

                    } catch (Exception e) {
                    }
                    Log.d("TAG", "TAG Pressed: " + message);
                    return false;
                }
            });
            audioWidget.show(100, 200);
            audioWidget.controller().start();

            audioWidget.controller().onControlsClickListener(new OnControlsClickListener() {
                @Override
                public boolean onPlaylistClicked() {
                    Log.e("songserrvice", "view onPlaylistClicked");
                    return false;
                }

                @Override
                public void onPlaylistLongClicked() {
                }

                @Override
                public void onPreviousClicked() {
                    Controls.previousControl(getApplicationContext());
                }

                @Override
                public void onPreviousLongClicked() {

                }

                @Override
                public boolean onPlayPauseClicked() {
                    boolean x = PlayerConstants.SONG_PAUSED;
                    if (PlayerConstants.SONG_PAUSED) {
                        Controls.playControl(getApplicationContext());
                        audioWidget.controller().start();
                    } else {
                        Controls.pauseControl(getApplicationContext());
                        audioWidget.controller().pause();
                    }
                    return false;
                }

                @Override
                public void onPlayPauseLongClicked() {

                }

                @Override
                public void onNextClicked() {
                    Controls.nextControl(getApplicationContext());
                }

                @Override
                public void onNextLongClicked() {

                }

                @Override
                public void onAlbumClicked() {
                    Intent i = new Intent(SongService.this, ItemLessonActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.putExtra("id", PlayerConstants.ID_LEASSON);
                    startActivity(i);
                }

                @Override
                public void onAlbumLongClicked() {

                }
            });
            audioWidget.controller().onWidgetStateChangedListener(new OnWidgetStateChangedListener() {
                @Override
                public void onWidgetStateChanged(@NonNull State state) {
                    // widget state changed (COLLAPSED, EXPANDED, REMOVED)
                    if (state == State.COLLAPSED) {
                        Log.e("songserrvice", "view COLLAPSED");
                    }
                    if (state == State.EXPANDED) {
                        Log.e("songserrvice", "view EXPANDED");
                    }
                    if (state == State.REMOVED) {
                        Controls.pauseControl(getApplicationContext());
                    }
                }

                @Override
                public void onWidgetPositionChanged(int cx, int cy) {
                    // widget position change. Save coordinates here to reuse them next time AudioWidget.show(int, int) called.
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    /**
     * Notification
     * Custom Bignotification is available from API 16
     */
    @SuppressLint("NewApi")
    private void newNotification() {

        String songName = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getName();
        String albumName = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getContext();
        RemoteViews simpleContentView = new RemoteViews(getApplicationContext().getPackageName(), R.layout.custom_notification);
        RemoteViews expandedView = new RemoteViews(getApplicationContext().getPackageName(), R.layout.big_notification);

        Notification notification = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_music)
                .setContentTitle(songName).build();

        setListeners(simpleContentView);
        setListeners(expandedView);

        notification.contentView = simpleContentView;
        if (currentVersionSupportBigNotification) {
            notification.bigContentView = expandedView;
        }
        try {
            long albumId = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getId();
            RealmLeason l = new RealmLeason(getApplicationContext());
            Lesson ls = l.getleassongbyid(PlayerConstants.ID_LEASSON);
            Bitmap albumArt = UtilFunctions.getDefaultAlbumArt(ls.getImg());
            if (albumArt != null) {
                notification.contentView.setImageViewBitmap(R.id.imageViewAlbumArt, albumArt);
                if (currentVersionSupportBigNotification) {
                    notification.bigContentView.setImageViewBitmap(R.id.imageViewAlbumArt, albumArt);
                }
            } else {
                notification.contentView.setImageViewResource(R.id.imageViewAlbumArt, R.drawable.default_album_art);
                if (currentVersionSupportBigNotification) {
                    notification.bigContentView.setImageViewResource(R.id.imageViewAlbumArt, R.drawable.default_album_art);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (PlayerConstants.SONG_PAUSED) {
            notification.contentView.setViewVisibility(R.id.btnPause, View.GONE);
            notification.contentView.setViewVisibility(R.id.btnPlay, View.VISIBLE);

            if (currentVersionSupportBigNotification) {
                notification.bigContentView.setViewVisibility(R.id.btnPause, View.GONE);
                notification.bigContentView.setViewVisibility(R.id.btnPlay, View.VISIBLE);
            }
        } else {
            notification.contentView.setViewVisibility(R.id.btnPause, View.VISIBLE);
            notification.contentView.setViewVisibility(R.id.btnPlay, View.GONE);

            if (currentVersionSupportBigNotification) {
                notification.bigContentView.setViewVisibility(R.id.btnPause, View.VISIBLE);
                notification.bigContentView.setViewVisibility(R.id.btnPlay, View.GONE);
            }
        }

        notification.contentView.setTextViewText(R.id.textSongName, songName);
        notification.contentView.setTextViewText(R.id.textAlbumName, albumName);
        if (currentVersionSupportBigNotification) {
            notification.bigContentView.setTextViewText(R.id.textSongName, songName);
            notification.bigContentView.setTextViewText(R.id.textAlbumName, albumName);
        }
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        startForeground(NOTIFICATION_ID, notification);
    }

    /**
     * Notification click listeners
     *
     * @param view
     */
    public void setListeners(RemoteViews view) {
        Intent previous = new Intent(NOTIFY_PREVIOUS);
        Intent delete = new Intent(NOTIFY_DELETE);
        Intent pause = new Intent(NOTIFY_PAUSE);
        Intent next = new Intent(NOTIFY_NEXT);
        Intent play = new Intent(NOTIFY_PLAY);

        PendingIntent pPrevious = PendingIntent.getBroadcast(getApplicationContext(), 0, previous, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btnPrevious, pPrevious);

        PendingIntent pDelete = PendingIntent.getBroadcast(getApplicationContext(), 0, delete, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btnDelete, pDelete);

        PendingIntent pPause = PendingIntent.getBroadcast(getApplicationContext(), 0, pause, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btnPause, pPause);

        PendingIntent pNext = PendingIntent.getBroadcast(getApplicationContext(), 0, next, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btnNext, pNext);

        PendingIntent pPlay = PendingIntent.getBroadcast(getApplicationContext(), 0, play, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btnPlay, pPlay);

    }

    public void saveHistory() {
        if (mp != null) {
            RealmLeason rl = new RealmLeason(getApplicationContext());
            History h = new History();
            int x = mp.getCurrentPosition();
            h.setLongs(x);
            Date t = new Date();
            h.setTimes(t.getTime());
            h.setType(PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getType());
            if (x > 20000) rl.addHistorybyid(h, PlayerConstants.ID_LEASSON);
            Log.e("luu history", "id" + PlayerConstants.ID_LEASSON + "time" + Constant.df.format(t) + "long :" + mp.getCurrentPosition());
        }
    }

    @Override
    public void onDestroy() {
        if (mp != null) {
            saveHistory();
            mp.stop();
            mp = null;
        }
        super.onDestroy();
    }

    /**
     * Play song, Update Lockscreen fields
     *
     * @param songPath
     * @param data
     */
    @SuppressLint("NewApi")
    private void playSong(String songPath, MP3 data) {
        try {
            if (currentVersionSupportLockScreenControls) {
                UpdateMetadata(data);
                remoteControlClient.setPlaybackState(RemoteControlClient.PLAYSTATE_PLAYING);
            }
            mp.reset();
            PrefManager pre = new PrefManager(getApplicationContext());

            if (pre.isRemote() == false) {
                Log.e("location", songPath);
                mp.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + songPath);
            } else {
                Log.e("location", Constant.DATA_URL + songPath);
                mp.setDataSource(Constant.DATA_URL + songPath);
            }
            mp.prepare();
            LessonActivity.showloadding();
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    LessonActivity.hintloadding();
                }
            });
            mp.start();
            timer.scheduleAtFixedRate(new MainTask(), 0, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("NewApi")
    private void RegisterRemoteClient() {
        remoteComponentName = new ComponentName(getApplicationContext(), new NotificationBroadcast().ComponentName());
        try {
            if (remoteControlClient == null) {
                audioManager.registerMediaButtonEventReceiver(remoteComponentName);
                Intent mediaButtonIntent = new Intent(Intent.ACTION_MEDIA_BUTTON);
                mediaButtonIntent.setComponent(remoteComponentName);
                PendingIntent mediaPendingIntent = PendingIntent.getBroadcast(this, 0, mediaButtonIntent, 0);
                remoteControlClient = new RemoteControlClient(mediaPendingIntent);
                audioManager.registerRemoteControlClient(remoteControlClient);
            }
            remoteControlClient.setTransportControlFlags(
                    RemoteControlClient.FLAG_KEY_MEDIA_PLAY |
                            RemoteControlClient.FLAG_KEY_MEDIA_PAUSE |
                            RemoteControlClient.FLAG_KEY_MEDIA_PLAY_PAUSE |
                            RemoteControlClient.FLAG_KEY_MEDIA_STOP |
                            RemoteControlClient.FLAG_KEY_MEDIA_PREVIOUS |
                            RemoteControlClient.FLAG_KEY_MEDIA_NEXT);
        } catch (Exception ex) {
        }
    }

    @SuppressLint("NewApi")
    private void UpdateMetadata(MP3 data) {
        if (remoteControlClient == null)
            return;
        MetadataEditor metadataEditor = remoteControlClient.editMetadata(true);
        metadataEditor.putString(MediaMetadataRetriever.METADATA_KEY_ALBUM, data.getContext());
        metadataEditor.putString(MediaMetadataRetriever.METADATA_KEY_ARTIST, Constant.typemp3[data.getType()]);
        metadataEditor.putString(MediaMetadataRetriever.METADATA_KEY_TITLE, data.getName());
        Lesson l = new RealmLeason(getApplicationContext()).getleassongbyid(PlayerConstants.ID_LEASSON);
        if (l.getIsrealy() > 0) mDummyAlbumArt = UtilFunctions.getDefaultAlbumArt(l.getImg());
        else {
            mDummyAlbumArt = BitmapFactory.decodeResource(getResources(), R.drawable.default_album_art);
        }
        metadataEditor.putBitmap(RemoteControlClient.MetadataEditor.BITMAP_KEY_ARTWORK, mDummyAlbumArt);
        metadataEditor.apply();
        audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
    }
}