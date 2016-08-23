package com.example.mypc.ezenglish.flagment;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.adapter.MP3Adapter;
import com.example.mypc.ezenglish.controls.Controls;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.MP3;
import com.example.mypc.ezenglish.service.SongService;
import com.example.mypc.ezenglish.util.Constant;
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
    ProgressBar progressBar;
    Context context;
    TextView textBufferDuration, textDuration;
    MP3Adapter customAdapter = null;
    ListView mediaListView;
    Lesson l;
    ArrayList<MP3> listmp3;
    boolean isplay;

    public static Mp3Fragment newInstance(Lesson l) {
        Mp3Fragment fragmentFirst = new Mp3Fragment(l);
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;

    }

    public Mp3Fragment(Lesson l) {
        this.l = l;
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
        textNowPlaying.setText(l.getName());

        if (isServiceRunning) {
            updateUI();
            isplay = true;
        }
        changeButton();
        return view;
    }

    public void changeButton() {
        boolean x = PlayerConstants.SONG_PAUSED;
        Log.e("dung", "" + x);
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
            String composer = Constant.typemp3[PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getType()];
            textAlbumArtist.setText(songName);
            textComposer.setText(composer);
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
        listmp3 = new ArrayList<>();
        for (MP3 x : l.getAll()) {
            listmp3.add(x);
        }
        PlayerConstants.SONGS_LIST = listmp3;
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

        customAdapter = new MP3Adapter(context, R.layout.custom_list, listmp3);
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
                btnPause.setVisibility(View.GONE);
                btnPlay.setVisibility(View.VISIBLE);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Controls.playControl(context);
                btnPause.setVisibility(View.VISIBLE);
                btnPlay.setVisibility(View.GONE);
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
