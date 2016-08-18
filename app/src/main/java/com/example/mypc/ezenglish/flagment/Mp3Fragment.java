package com.example.mypc.ezenglish.flagment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.adapter.CustomAdapter;
import com.example.mypc.ezenglish.adapter.MP3Adapter;
import com.example.mypc.ezenglish.controls.Controls;
import com.example.mypc.ezenglish.model.MP3;
import com.example.mypc.ezenglish.service.SongService;
import com.example.mypc.ezenglish.util.PlayerConstants;
import com.example.mypc.ezenglish.util.UtilFunctions;

import java.util.ArrayList;

/**
 * Created by Quylt on 8/18/2016.
 */
public class Mp3Fragment extends Fragment {

    Button btnBack;
    Button btnPause;
    Button btnNext;
    Button btnPlay;
    TextView textNowPlaying;
    TextView textAlbumArtist;
    TextView textComposer;
    LinearLayout linearLayoutPlayer;
    ProgressBar progressBar;
    Context context;
    TextView textBufferDuration, textDuration;
    MP3Adapter customAdapter = null;
    ListView mediaListView;
    ArrayList<MP3> list;

    public static Mp3Fragment newInstance() {
        Mp3Fragment fragmentFirst = new Mp3Fragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mp3, container, false);
        init(view);
        boolean isServiceRunning = UtilFunctions.isServiceRunning(SongService.class.getName(), context);
        if (isServiceRunning) {
            updateUI();
        }
        changeButton();
        return view;
    }

    public void changeButton() {
        if (PlayerConstants.SONG_PAUSED) {
            btnPause.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);
        } else {
            btnPause.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.GONE);
        }
    }

    private void updateUI() {
        try {
            String songName = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getName();
            String artist = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getContext();
            String album = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getLocation();
            String composer = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getType();

            textNowPlaying.setText(songName);
            textAlbumArtist.setText(artist + " - " + album);
            if (composer != null && composer.length() > 0) {
                textComposer.setVisibility(View.VISIBLE);
                textComposer.setText(composer);
            } else {
                textComposer.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            long albumId = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getId();
            Bitmap albumArt = UtilFunctions.blur("/original/1/avatar.jpg", context);
            linearLayoutPlayer.setBackgroundDrawable(new BitmapDrawable(albumArt));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getViews(View view) {

        context = getContext();
        btnBack = (Button) view.findViewById(R.id.btnBack);
        btnPause = (Button) view.findViewById(R.id.btnPause);
        btnNext = (Button) view.findViewById(R.id.btnNext);
        btnPlay = (Button) view.findViewById(R.id.btnPlay);
        mediaListView = (ListView) view.findViewById(R.id.listViewMusic);
        textNowPlaying = (TextView) view.findViewById(R.id.textNowPlaying);
        linearLayoutPlayer = (LinearLayout) view.findViewById(R.id.linearLayoutPlayer);
        textAlbumArtist = (TextView) view.findViewById(R.id.textAlbumArtist);
        textComposer = (TextView) view.findViewById(R.id.textComposer);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        textBufferDuration = (TextView) view.findViewById(R.id.textBufferDuration);
        textDuration = (TextView) view.findViewById(R.id.textDuration);
        textNowPlaying.setSelected(true);
        textAlbumArtist.setSelected(true);
    }

    public void init(View view) {
        getViews(view);
        setListeners();
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        PlayerConstants.PROGRESSBAR_HANDLER = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Integer i[] = (Integer[]) msg.obj;
                textBufferDuration.setText(UtilFunctions.getDuration(i[0]));
                textDuration.setText(UtilFunctions.getDuration(i[1]));
                progressBar.setProgress(i[2]);
            }
        };

        setListItems();
    }

    private void setListItems() {
        list = PlayerConstants.SONGS_LIST;
        customAdapter = new MP3Adapter(context, R.layout.custom_list, list);
        mediaListView.setAdapter(customAdapter);
        mediaListView.setFastScrollEnabled(true);
    }

    private void setListeners() {
        mediaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {
                PlayerConstants.SONG_PAUSED = false;
                PlayerConstants.SONG_NUMBER = position;
                boolean isServiceRunning = UtilFunctions.isServiceRunning(SongService.class.getName(), context);
                if (!isServiceRunning) {
                    Intent i = new Intent(context, SongService.class);
                    context.startService(i);
                } else {
                    PlayerConstants.SONG_CHANGE_HANDLER.sendMessage(PlayerConstants.SONG_CHANGE_HANDLER.obtainMessage());
                }
                updateUI();
                changeButton();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controls.previousControl(context);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controls.pauseControl(context);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Controls.playControl(context);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controls.nextControl(context);
            }
        });

    }
}
