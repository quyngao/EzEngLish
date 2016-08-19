package com.example.mypc.ezenglish.flagment;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.model.Lesson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by MyPC on 20/08/2016.
 */
public class TextFragment extends Fragment {

    Lesson l;
    Context context;
    TextView textNowPlaying;
    TextView textAlbumArtist;
    TextView textdoc;

    public static TextFragment newInstance(Lesson l) {
        TextFragment fragmentFirst = new TextFragment(l);
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    public TextFragment(Lesson l) {
        this.l = l;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        init(view);
        context = getActivity();
        return view;
    }

    public void init(View view) {
        textNowPlaying = (TextView) view.findViewById(R.id.textNowPlaying);
        textAlbumArtist = (TextView) view.findViewById(R.id.textAlbumArtist);
        textdoc = (TextView) view.findViewById(R.id.text_doc);

        textNowPlaying.setText(l.getName());
        textAlbumArtist.setText(l.getContext());
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
