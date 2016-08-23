package com.example.mypc.ezenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.model.Recod;
import com.example.mypc.ezenglish.util.UtilFunctions;

import java.util.ArrayList;

import io.realm.RealmList;

/**
 * Created by Quylt on 8/12/2016.
 */
public class RecordAdapter extends ArrayAdapter<Recod> {
    RealmList<Recod> listrecord;
    Context context;
    LayoutInflater inflator;

    public RecordAdapter(Context context, int resource, RealmList<Recod> listOfSongs) {
        super(context, resource, listOfSongs);
        this.listrecord = listOfSongs;
        this.context = context;
        inflator = LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView textname, textdate, textViewDuration;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = convertView;
        if (convertView == null) {
            myView = inflator.inflate(R.layout.custom_list, parent, false);
            holder = new ViewHolder();
            holder.textname = (TextView) myView.findViewById(R.id.textViewSongName);
            holder.textdate = (TextView) myView.findViewById(R.id.textViewArtist);
            holder.textViewDuration = (TextView) myView.findViewById(R.id.textViewDuration);
            myView.setTag(holder);
        } else {
            holder = (ViewHolder) myView.getTag();
        }
        Recod detail = listrecord.get(position);
        holder.textname.setText(detail.getName());
        holder.textdate.setText(detail.getTime());
        holder.textViewDuration.setText(UtilFunctions.getDurationFile(detail.getLocation()));
        return myView;
    }

    ViewHolder holder;
}
