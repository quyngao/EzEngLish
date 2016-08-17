package com.example.mypc.ezenglish.flagment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.util.PlayerConstants;
import com.example.mypc.ezenglish.util.UtilFunctions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Quylt on 8/17/2016.
 */
public class TextFlagment extends Fragment {


    static TextView textNowPlaying;
    static TextView textAlbumArtist;
    static TextView textComposer;
    static TextView textdoc;
    static LinearLayout linearLayoutPlayer;
    static Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_text, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        init();

    }

    private void init() {
        getViews();
        updateUI();

    }

    private static void updateUI() {
        try {
            String songName = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getName();
            String artist = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getContext();
            String album = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getLocation();
            String composer = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER).getType();
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/original/1/voca.txt");
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
            } catch (IOException e) {
            }
            textdoc.setText(text.toString());
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
            Bitmap albumArt = UtilFunctions.blur("/original/1/avatar.jpg", context);
            linearLayoutPlayer.setBackgroundDrawable(new BitmapDrawable(albumArt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getViews() {
        textdoc = (TextView) getActivity().findViewById(R.id.text_doc);

        textNowPlaying = (TextView) getActivity().findViewById(R.id.textNowPlaying);
        linearLayoutPlayer = (LinearLayout) getActivity().findViewById(R.id.linearLayoutPlayer);
        textAlbumArtist = (TextView) getActivity().findViewById(R.id.textAlbumArtist);
        textComposer = (TextView) getActivity().findViewById(R.id.textComposer);

        textNowPlaying.setSelected(true);
        textAlbumArtist.setSelected(true);
    }
}
