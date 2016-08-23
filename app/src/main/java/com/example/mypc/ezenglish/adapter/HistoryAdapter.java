package com.example.mypc.ezenglish.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.model.History;
import com.example.mypc.ezenglish.model.Recod;
import com.example.mypc.ezenglish.util.Constant;
import com.example.mypc.ezenglish.util.UtilFunctions;

import java.util.Date;

import io.realm.RealmList;

/**
 * Created by Quylt on 8/22/2016.
 */
public class HistoryAdapter extends ArrayAdapter<History> {
    RealmList<History> listhisto;
    Context context;
    LayoutInflater inflator;
    ViewHolder holder;

    public HistoryAdapter(Context context, int resource, RealmList<History> listOfSongs) {
        super(context, resource, listOfSongs);
        this.listhisto = listOfSongs;
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
            myView = inflator.inflate(R.layout.list_history, parent, false);
            holder = new ViewHolder();
            holder.textname = (TextView) myView.findViewById(R.id.tv_typehis);
            holder.textdate = (TextView) myView.findViewById(R.id.tv_date);
            holder.textViewDuration = (TextView) myView.findViewById(R.id.tv_long);
            myView.setTag(holder);
        } else {
            holder = (ViewHolder) myView.getTag();
        }
        History detail = listhisto.get(position);
        String s = Constant.df.format(new Date(detail.getTimes()));
        Log.e("time his", "" + s);
        holder.textname.setText(Constant.typemp3[detail.getType()]);
        holder.textdate.setText(s);
        holder.textViewDuration.setText(UtilFunctions.getDuration(detail.getLongs()));
        return myView;
    }


}