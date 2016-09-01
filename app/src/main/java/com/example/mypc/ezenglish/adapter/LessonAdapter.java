package com.example.mypc.ezenglish.adapter;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.activity.LessonActivity;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.util.Constant;
import com.example.mypc.ezenglish.util.PrefManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 20/08/2016.
 */
public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.MyViewHolder> {


    private Context mContext;
    private ArrayList<Lesson> albumList;
    PrefManager prefManager;

    public LessonAdapter(Context mContext, List<Lesson> albumList) {
        this.mContext = mContext;
        this.albumList = (ArrayList<Lesson>) albumList;
        prefManager = new PrefManager(mContext);
    }

    public LessonAdapter(Context mContext, ArrayList<Lesson> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_lesson, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Lesson album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getContext());

        holder.state.setText(Constant.stateslesson[album.getIsrealy()]);

        if (album.getIsrealy() > 0) {
            Glide.with(mContext).load(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + album.getImg())).into(holder.thumbnail);
            holder.overflow.setBackgroundResource(R.drawable.ic_starblu);
            if (album.getIsrealy() == 2) holder.overflow.setBackgroundResource(R.drawable.icstar);
        } else {
            Glide.with(mContext).load(Constant.DATA_URL + album.getImg()).into(holder.thumbnail);
            holder.overflow.setBackgroundResource(R.drawable.ic_down);
        }
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.thumbnail, position, album.getIsrealy());
            }
        });

        holder.bg_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.thumbnail, position, album.getIsrealy());
            }
        });
//        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (albumList.get(position).getIsrealy() == 1) {
//                    LessonActivity.nextActivity(position);
//                } else if (albumList.get(position).getIsrealy() == 0) {
//                    LessonActivity.showconfirmdow(position);
//                } else if (albumList.get(position).getIsrealy() == 2) {
//                    LessonActivity.nextActivity(position);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    private void showPopupMenu(View view, int i, int state) {

        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        Log.e("show ", "online ?" + albumList.get(i).getName() + " state" + albumList.get(i).getIsrealy());
        if (albumList.get(i).getIsrealy() == 0)
            inflater.inflate(R.menu.menu_album, popup.getMenu());
        else if (albumList.get(i).getIsrealy() == 1)
            inflater.inflate(R.menu.menu_local, popup.getMenu());
        else if (albumList.get(i).getIsrealy() == 2)
            inflater.inflate(R.menu.menu_now, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(i));
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public int id;

        public MyMenuItemClickListener(int id) {
            this.id = id;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.replay:
                    LessonActivity.ChoiseLesson(id);
                    return true;
                case R.id.leared:
                    LessonActivity.ChoiseLesson(id);
                    return true;
                case R.id.view:
                    LessonActivity.nextActivity(id);
                    return true;
                case R.id.down:
                    LessonActivity.showconfirmdow(id);
                    return true;
                case R.id.playnow:
                    LessonActivity.ChoiseLesson(id);
                    return true;
                default:
            }
            return false;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count, state;
        public ImageView thumbnail, overflow;
        public RelativeLayout bg_c;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            state = (TextView) view.findViewById(R.id.state);
            bg_c = (RelativeLayout) view.findViewById(R.id.bg_c);
        }
    }
}
