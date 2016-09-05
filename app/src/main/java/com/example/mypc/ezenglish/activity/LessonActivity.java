package com.example.mypc.ezenglish.activity;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.adapter.LessonAdapter;
import com.example.mypc.ezenglish.controls.Controls;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.MP3;
import com.example.mypc.ezenglish.model.Voca;
import com.example.mypc.ezenglish.model.VocaData;
import com.example.mypc.ezenglish.realm.DataDummyLocal;
import com.example.mypc.ezenglish.realm.RealmLeason;
import com.example.mypc.ezenglish.service.SongService;
import com.example.mypc.ezenglish.util.AlarmUtil;
import com.example.mypc.ezenglish.util.Constant;
import com.example.mypc.ezenglish.util.PlayerConstants;
import com.example.mypc.ezenglish.util.PrefManager;
import com.example.mypc.ezenglish.util.UtilFunctions;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Quylt on 8/10/2016.
 */
public class LessonActivity extends Activity {
    static TextView playingSong;
    Button btnPlayer;
    static Button btnPause, btnPlay, btnNext, btnPrevious;
    Button btnStop;
    LinearLayout mediaLayout;
    static LinearLayout linearLayoutPlayingSong;
    ProgressBar progressBar;
    TextView textBufferDuration, textDuration;
    static ImageView imageViewAlbumArt;
    static Context context;
    static RealmLeason rl;
    static ProgressDialog progressDialog;
    static DownloadManager dm;
    static Lesson l;
    static ArrayList<Lesson> list;

    static List<Long> callback = new ArrayList<>();
    private RecyclerView recyclerView;
    private LessonAdapter adapter;
    PrefManager prefManager;

    public static void showloadding() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Waitting");
        progressDialog.setTitle("Downloading ... ");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public static void delete(long l) {
        for (int i = 0; i < callback.size(); i++) {
            if (callback.get(i) == Long.parseLong("" + l)) {
                callback.remove(i);
                return;
            }
        }
    }

    public static void showconfirmdowfirst(final int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Download First...");
        alertDialog.setMessage("Are you sure you want download for lesson?" + list.get(position).getName());
        alertDialog.setIcon(R.drawable.icon_home);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dowloading(list.get(position));
                showloadding();
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        alertDialog.show();
    }

    public static void hintloadding() {
        progressDialog.cancel();
    }

    public static void showconfirmdow(final int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Download...");
        alertDialog.setMessage("Are you sure you want download  lesson?" + list.get(position).getName());
        alertDialog.setIcon(R.drawable.icon_home);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dowloading(list.get(position));
                showloadding();
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        alertDialog.show();
    }

    public static void dowloading(Lesson lx) {
        l = lx;
        showloadding();
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/original/" + l.getId());
        if (!direct.exists()) {
            direct.mkdirs();
        }
        dm = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(Constant.DATA_URL + l.getImg()));
        request.setDestinationInExternalPublicDir("/original/" + l.getId() + "/", "avatar.jpg");
        long x = dm.enqueue(request);
        callback.add(x);

        request = new DownloadManager.Request(
                Uri.parse(Constant.DATA_URL + l.getDoc().getLocation()));
        request.setDestinationInExternalPublicDir("/original/" + l.getId() + "/", "voca.txt");
        x = dm.enqueue(request);
        callback.add(x);

        for (MP3 mp3 : l.getAll()) {
            request = new DownloadManager.Request(
                    Uri.parse(Constant.DATA_URL + mp3.getLocation()));
            request.setDestinationInExternalPublicDir("/original/" + l.getId() + "/", mp3.getName() + ".mp3");
            x = dm.enqueue(request);
            callback.add(x);
        }
        int i = 1;
        for (Voca voca : l.getVocas()) {
            request = new DownloadManager.Request(
                    Uri.parse(Constant.DATA_URL + voca.getImg()));
            request.setDestinationInExternalPublicDir("/original/" + l.getId() + "/", i + ".jpg");
            i++;
            x = dm.enqueue(request);
            callback.add(x);
        }

    }

    static BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, Intent intent) {
            String action = intent.getAction();
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                long downloadId = intent.getLongExtra(
                        DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(downloadId);
                Cursor c = dm.query(query);
                if (c.moveToFirst()) {
                    int columnIndex = c
                            .getColumnIndex(DownloadManager.COLUMN_STATUS);
                    if (DownloadManager.STATUS_SUCCESSFUL == c
                            .getInt(columnIndex)) {
                        delete(downloadId);
                        Log.e("ok", "done" + downloadId + " --  " + callback.size());
                        if (callback.size() == 7) {
                            hintloadding();
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                            alertDialog.setTitle("Download...");
                            alertDialog.setMessage("Are you sure learn now  lesson?, 1 lesson you need to learn at least 7 days" + l.getName());
                            alertDialog.setIcon(R.drawable.icon_home);
                            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String day = AlarmUtil.df.format(Calendar.getInstance().getTime());
                                    for (int i = 0; i < list.size(); i++) {
                                        if (list.get(i).getIsrealy() == 2)
                                            rl.saveLocal(list.get(i), 1, "");
                                    }
                                    rl.saveLocal(l, 2, day);
                                    List<VocaData> listvoca = new ArrayList<VocaData>();
                                    for (Voca vc : l.getVocas()) {
                                        VocaData tmp = new VocaData();
                                        tmp.copyData(vc);
                                        listvoca.add(tmp);
                                    }
                                    PrefManager prefManager = new PrefManager(context);
                                    prefManager.setlistvoca(listvoca);
                                    prefManager.setlearnid(l.getId());
                                    prefManager.setlearnid(l.getId());
                                    list = rl.getAllLeasson();
                                    nextActivity(l.getId() - 1);
                                    dialog.cancel();
                                }
                            });
                            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    rl.saveLocal(l, 1, "");
                                    list = rl.getAllLeasson();
                                    changeUI();
                                    dialog.cancel();
                                }
                            });
                            alertDialog.show();
                            Log.e("ok", "done all");
                        }
                    }
                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 103) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
            }
        }
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        context = LessonActivity.this;
        rl = new RealmLeason(this);
        prefManager = new PrefManager(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 103);
        }
        init();
    }

    private void init() {
        getViews();
        setListeners();
        playingSong.setSelected(true);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        if (PlayerConstants.SONGS_LIST.size() <= 0) {
            PlayerConstants.SONGS_LIST = UtilFunctions.listOfSongs(getApplication(), 1);
        }
        setListItems();
        int id = prefManager.getlearnid();
        if (id == 0) {
            showconfirmdowfirst(0);
        }
    }

    private void setListItems() {
        list = rl.getAllLeasson();
        Log.e("all lesson", "" + list.size());
        for (Lesson x : list) {
            new DataDummyLocal().showLesson(x);
        }
        adapter = new LessonAdapter(this, list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public static void nextActivity(int id) {
        PlayerConstants.ID_LEASSON = list.get(id).getId();
        Intent i = new Intent(context, ItemLessonActivity.class);
        PrefManager pr = new PrefManager(context);
        if (list.get(id).getIsrealy() == 0) pr.setremote(true);
        else pr.setremote(false);
        i.putExtra("id", list.get(id).getId());
        context.startActivity(i);
    }

    public static void reLesson(int id) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Relearn...");
        l = list.get(id);
        alertDialog.setMessage("You sure you want to relearn?" + l.getName());
        alertDialog.setIcon(R.drawable.icon_home);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String day = AlarmUtil.df.format(Calendar.getInstance().getTime());
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getIsrealy() == 2) rl.saveLocal(list.get(i), 1, "");
                }
                rl.saveLocal(l, 2, day);
                List<VocaData> listvoca = new ArrayList<VocaData>();
                for (Voca vc : l.getVocas()) {
                    VocaData tmp = new VocaData();
                    tmp.copyData(vc);
                    listvoca.add(tmp);
                }
                PrefManager prefManager = new PrefManager(context);
                prefManager.setlistvoca(listvoca);
                prefManager.setlearnid(l.getId());
                list = rl.getAllLeasson();
                nextActivity(l.getId() - 1);
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();

    }

    public static void ChoiseLesson(int id) {
        l = list.get(id);
        PlayerConstants.ID_LEASSON = l.getId();
        PlayerConstants.SONGS_LIST = UtilFunctions.listOfSongs(context, l.getId());
        PlayerConstants.SONG_PAUSED = false;
        PlayerConstants.SONG_NUMBER = 0;
        boolean isServiceRunning = UtilFunctions.isServiceRunning(SongService.class.getName(), context);
        if (l.getIsrealy() == 0) new PrefManager(context).setremote(true);
        else new PrefManager(context).setremote(false);
        if (!isServiceRunning) {
            Intent i = new Intent(context, SongService.class);
            context.startService(i);
        } else {
            PlayerConstants.SONG_CHANGE_HANDLER.sendMessage(PlayerConstants.SONG_CHANGE_HANDLER.obtainMessage());
        }
        updateUI();
        changeButton();

    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void getViews() {
        playingSong = (TextView) findViewById(R.id.textNowPlaying);
        btnPlayer = (Button) findViewById(R.id.btnMusicPlayer);
        mediaLayout = (LinearLayout) findViewById(R.id.linearLayoutMusicList);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        linearLayoutPlayingSong = (LinearLayout) findViewById(R.id.linearLayoutPlayingSong);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnStop = (Button) findViewById(R.id.btnStop);
        textBufferDuration = (TextView) findViewById(R.id.textBufferDuration);
        textDuration = (TextView) findViewById(R.id.textDuration);
        imageViewAlbumArt = (ImageView) findViewById(R.id.imageViewAlbumArt);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void setListeners() {
        btnPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LessonActivity.this, ItemLessonActivity.class);
                if (l.getIsrealy() > 0) {
                    prefManager.setremote(true);
                    i.putExtra("id", l.getId());
                    startActivity(i);
                }

            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controls.playControl(getApplicationContext());
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controls.pauseControl(getApplicationContext());
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Controls.nextControl(getApplicationContext());
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Controls.previousControl(getApplicationContext());
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SongService.class);
                stopService(i);
                linearLayoutPlayingSong.setVisibility(View.GONE);
            }
        });
        imageViewAlbumArt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(LessonActivity.this, ItemLessonActivity.class);
                if (l.getIsrealy() > 0) {
                    prefManager.setremote(true);
                    i.putExtra("id", l.getId());
                    startActivity(i);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            boolean isServiceRunning = UtilFunctions.isServiceRunning(SongService.class.getName(), getApplicationContext());
            if (isServiceRunning) {
                updateUI();
            } else {
                linearLayoutPlayingSong.setVisibility(View.GONE);
            }
            changeButton();
            PlayerConstants.PROGRESSBAR_HANDLER = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Integer i[] = (Integer[]) msg.obj;
                    textBufferDuration.setText(UtilFunctions.getDuration(i[0]));
                    textDuration.setText(UtilFunctions.getDuration(i[1]));
                    progressBar.setProgress(i[2]);
                }
            };
            registerReceiver(receiver, new IntentFilter(
                    DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        } catch (Exception e) {
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @SuppressWarnings("deprecation")
    public static void updateUI() {
        try {

            MP3 data = PlayerConstants.SONGS_LIST.get(PlayerConstants.SONG_NUMBER);
            RealmLeason l = new RealmLeason(context);
            Lesson ls = l.getleassongbyid(PlayerConstants.ID_LEASSON);
            playingSong.setText(data.getName() + " " + data.getContext() + "-" + data.getType());

            if (ls.getIsrealy() > 0) {
                Glide.with(context).load(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + ls.getImg())).into(imageViewAlbumArt);
            } else {
                Glide.with(context).load(Constant.DATA_URL + ls.getImg()).into(imageViewAlbumArt);
            }
            linearLayoutPlayingSong.setVisibility(View.VISIBLE);
        } catch (Exception e) {
        }
    }

    public static void changeButton() {
        if (PlayerConstants.SONG_PAUSED) {
            btnPause.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);
        } else {
            btnPause.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.GONE);
        }
    }

    public static void changeUI() {
        updateUI();
        changeButton();
    }
}