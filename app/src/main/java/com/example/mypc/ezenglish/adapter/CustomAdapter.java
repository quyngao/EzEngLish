package com.example.mypc.ezenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.model.MP3;
import com.example.mypc.ezenglish.util.UtilFunctions;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<MP3> {

    ArrayList<MP3> listOfSongs;
    Context context;
    LayoutInflater inflator;

    public CustomAdapter(Context context, int resource, ArrayList<MP3> listOfSongs) {
        super(context, resource, listOfSongs);
        this.listOfSongs = listOfSongs;
        this.context = context;
        inflator = LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView textViewSongName, textViewArtist, textViewDuration;
    }

    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = convertView;
        if (convertView == null) {
            myView = inflator.inflate(R.layout.custom_list, parent, false);
            holder = new ViewHolder();
            holder.textViewSongName = (TextView) myView.findViewById(R.id.textViewSongName);
            holder.textViewArtist = (TextView) myView.findViewById(R.id.textViewArtist);
            holder.textViewDuration = (TextView) myView.findViewById(R.id.textViewDuration);
            myView.setTag(holder);
        } else {
            holder = (ViewHolder) myView.getTag();
        }
        MP3 detail = listOfSongs.get(position);
        holder.textViewSongName.setText(detail.getName());
        holder.textViewArtist.setText(detail.getContext() + " - " + detail.getType());
        holder.textViewDuration.setText(UtilFunctions.getDuration(0));
        return myView;
    }
}
