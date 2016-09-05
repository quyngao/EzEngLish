package com.example.mypc.ezenglish.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.activity.LessonActivity;
import com.example.mypc.ezenglish.datafirebase.TopicFB;

import java.util.List;

/**
 * Created by MyPC on 27/08/2016.
 */
public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder> {
    private Context mContext;
    private List<TopicFB> albumList;

    public TopicAdapter(Context mContext, List<TopicFB> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_topic, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final TopicFB album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getDescription());


        holder.bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0)
                    mContext.startActivity(new Intent(mContext, LessonActivity.class));
                else Toast.makeText(mContext, "updating", Toast.LENGTH_SHORT).show();
            }
        });
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0)
                    mContext.startActivity(new Intent(mContext, LessonActivity.class));
                else Toast.makeText(mContext, "updated", Toast.LENGTH_SHORT).show();
            }
        });


        Glide.with(mContext).load("").fitCenter().placeholder(Integer.parseInt(album.getImg())).into(holder.thumbnail);

        if (position == 0) holder.overflow.setBackgroundResource(R.drawable.ic_starblu);
        else holder.overflow.setBackgroundResource(R.drawable.ic_stargray);

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        public LinearLayout bg;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            bg = (LinearLayout) view.findViewById(R.id.bg_topic);
        }
    }
}
