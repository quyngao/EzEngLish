package com.example.mypc.ezenglish.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.model.Voca;
import com.example.mypc.ezenglish.util.UtilFunctions;

import java.util.ArrayList;

/**
 * Created by Quylt on 8/11/2016.
 */
public class VocaAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Voca> data;
    private static LayoutInflater inflater = null;

    public VocaAdapter(Activity a, ArrayList<Voca> list) {
        activity = a;
        data = list;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Voca getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView) vi.findViewById(R.id.title); // title
        TextView artist = (TextView) vi.findViewById(R.id.artist); // artist name
        TextView duration = (TextView) vi.findViewById(R.id.duration); // duration
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.list_image); // thumb image


        Voca song = data.get(i);
        title.setText(song.getName());
        artist.setText(song.getDescription());
        duration.setText(song.getTrans());

        Bitmap albumArt = UtilFunctions.getDefaultAlbumArt(data.get(i).getImg());
        Log.e("location", data.get(i).getImg());
        thumb_image.setBackgroundDrawable(new BitmapDrawable(albumArt));
        return vi;
    }
}
