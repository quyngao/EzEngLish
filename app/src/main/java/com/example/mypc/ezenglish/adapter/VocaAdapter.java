package com.example.mypc.ezenglish.adapter;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.callback.VocaClick;
import com.example.mypc.ezenglish.model.VocaData;

import java.io.File;
import java.util.List;

/**
 * Created by Quylt on 8/11/2016.
 */
public class VocaAdapter extends RecyclerView.Adapter<VocaAdapter.MyViewHolder> {
    private Context mContext;
    private List<VocaData> data;
    VocaClick vocaClick;

    public VocaAdapter(Context mContext, List<VocaData> data, VocaClick vocaClick) {
        this.mContext = mContext;
        this.data = data;
        this.vocaClick = vocaClick;
    }

    @Override
    public VocaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_voca, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VocaAdapter.MyViewHolder holder, final int position) {
        final VocaData album = data.get(position);
        holder.title.setText(album.getName());
        holder.tran.setText(album.getTrans());
        holder.des.setText(album.getDescription());
        holder.check.setChecked(album.isRed());

        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (vocaClick != null) {
                    vocaClick.onCheckBox(position, b);
                }
            }
        });
        holder.voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vocaClick.onItemClick(position);
            }
        });


        Glide.with(mContext).load(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + album.getImg())).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, tran, des;
        public ImageView thumbnail, voice;
        public CheckBox check;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            tran = (TextView) view.findViewById(R.id.tran);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            check = (CheckBox) view.findViewById(R.id.tv_checkbox);
            des = (TextView) view.findViewById(R.id.des);
            voice = (ImageView) view.findViewById(R.id.voice);


        }
    }
}
