package com.example.mypc.ezenglish.flagment;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.adapter.RecordAdapter;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.Recod;
import com.example.mypc.ezenglish.realm.RealmLeason;
import com.example.mypc.ezenglish.util.UtilFunctions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.RealmList;

/**
 * Created by Quylt on 8/18/2016.
 */
public class RecordFragment extends Fragment {
    Context mcontext;
    ListView listview;
    Button btn_play, btn_save, btn_record;
    TextView tv_time;
    TextView tv_nameplay;


    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;
    boolean isplay = false;
    boolean isrecord = false;
    boolean issave = false;
    RealmList<Recod> list;
    private Timer timer;
    RealmLeason rl;
    Lesson l;
    Recod recod;
    RecordAdapter recordAdapter = null;

    public static RecordFragment newInstance(Lesson l) {
        RecordFragment fragmentFirst = new RecordFragment(l);
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    public RecordFragment(Lesson l) {
        this.l = l;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_record, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mcontext = getActivity();
        init();
    }

    private void onRecord(boolean start) {
        if (start) {
            tv_nameplay.setText("Is Recording");
            startRecording();
        } else {
            tv_nameplay.setText("Completed Record");
            stopRecording();
        }
    }

    public void init() {
        getViews();
        setListeners();
    }

    public void setListeners() {

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isrecord == false) {
                    btn_record.setBackgroundResource(R.drawable.ic_record);
                    btn_play.setEnabled(false);
                    btn_save.setEnabled(false);

                } else {
                    btn_record.setBackgroundResource(R.drawable.ic_recording);
                    btn_play.setEnabled(true);
                    btn_save.setEnabled(true);
                }
                isrecord = !isrecord;
                onRecord(isrecord);
            }
        });
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isplay == false) {
                    btn_play.setBackgroundResource(R.drawable.ic_action_pause);
                    btn_record.setEnabled(false);
                    btn_save.setEnabled(true);
                } else {
                    btn_play.setBackgroundResource(R.drawable.ic_action_pauserecord);
                    btn_record.setEnabled(true);
                    btn_save.setEnabled(true);
                }
                isplay = !isplay;
                onPlay(isplay);
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savefile();
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                File file = new File(l.getRecods().get(index).getLocation());
                boolean deleted = file.delete();
                Log.e("delete", "" + deleted);
                rl.deleteRecordbyid(index, l);
                l = rl.getleassongbyid(l.getId());
                list = l.getRecods();
                recordAdapter.notifyDataSetChanged();
                issave = true;
                return true;
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long li) {
                if (issave == false && recod.getLocation() != null) {
                    savefile();
                }
                issave = true;
                l = rl.getleassongbyid(l.getId());
                list = l.getRecods();
                recod = list.get(i);
                if (isplay == false) {
                    btn_play.setBackgroundResource(R.drawable.ic_action_pause);
                    btn_record.setEnabled(false);
                    btn_save.setEnabled(true);
                } else {
                    btn_play.setBackgroundResource(R.drawable.ic_action_play);
                    btn_record.setEnabled(true);
                    btn_save.setEnabled(true);
                }
                isplay = !isplay;
                onPlay(isplay);
            }
        });
    }

    private class MainTask extends TimerTask {
        int time = 0;

        public void run() {
            time = time + 100;
            Message message = handler.obtainMessage();
            message.arg1 = time;
            handler.sendMessage(message);
        }
    }

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("time", "" + msg.arg1);
            if (mPlayer != null) {
                tv_time.setText(UtilFunctions.getDuration(mPlayer.getCurrentPosition()));
            }
            if (mRecorder != null) {
                tv_time.setText(UtilFunctions.getDuration(msg.arg1));
            }
        }
    };

    private void startRecording() {
        issave = false;
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        Calendar c = Calendar.getInstance();
        String mFileName = "/original/" + l.getId();
        mFileName += "/record" + c.getTime().getTime() + ".mp3";
        Log.e("mFileName", mFileName);
        recod.setLocation(mFileName);
        Log.e("location", recod.getLocation());
        mRecorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath() + recod.getLocation());
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
        }
        mRecorder.start();
        timer = new Timer();
        timer.scheduleAtFixedRate(new MainTask(), 0, 100);
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        timer.cancel();
    }

    public void onPlay(boolean start) {
        if (start) {
            tv_nameplay.setText("Play file: " + recod.getName());
            startPlaying(recod.getLocation());
        } else {
            tv_nameplay.setText("Completed");
            stopPlaying();
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
        timer.cancel();
    }

    public void startPlaying(String location) {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + location);
            mPlayer.prepare();
            mPlayer.start();
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    btn_play.setBackgroundResource(R.drawable.ic_action_play);
                    btn_record.setEnabled(true);
                    btn_save.setEnabled(true);
                    tv_nameplay.setText("Completed");
                    timer.cancel();
                }
            });
            timer = new Timer();
            timer.scheduleAtFixedRate(new MainTask(), 0, 100);
        } catch (IOException e) {
        }
    }

    public void savefile() {
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (issave == false) {
            LayoutInflater li = LayoutInflater.from(mcontext);
            View promptsView = li.inflate(R.layout.dialog_saverecord, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    mcontext);
            alertDialogBuilder.setView(promptsView);

            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.editTextDialogUserInput);
            String time = df.format(new Date());
            String s = "Record: " + l.getName() + "__" + time;
            userInput.setText(s);
            recod.setName(s);
            recod.setTime(time);
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Save",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    rl.addRecordbyid(recod, l.getId());
                                    l = rl.getleassongbyid(l.getId());
                                    list = l.getRecods();
                                    recordAdapter.notifyDataSetChanged();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    File file = new File(recod.getLocation());
                                    boolean deleted = file.delete();
                                    Log.e("delete", "" + deleted);
                                    dialog.cancel();
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            issave = true;
        }
    }

    public void getViews() {
        btn_play = (Button) getActivity().findViewById(R.id.btn_play);
        btn_record = (Button) getActivity().findViewById(R.id.btn_record);
        btn_save = (Button) getActivity().findViewById(R.id.btn_save);
        listview = (ListView) getActivity().findViewById(R.id.listrecord);
        tv_time = (TextView) getActivity().findViewById(R.id.text_timestic);
        tv_nameplay = (TextView) getActivity().findViewById(R.id.textNowPlaying);
        btn_play.setEnabled(false);
        btn_save.setEnabled(false);


        timer = new Timer();
        rl = new RealmLeason(mcontext);
        Log.e("ok", "" + l.getRecods().size());
        list = l.getRecods();

        recordAdapter = new RecordAdapter(mcontext, R.layout.custom_list, list);
        listview.setAdapter(recordAdapter);
        listview.setFastScrollEnabled(true);
        recod = new Recod();

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
        if (issave == false && recod.getLocation() != null) {
            File file = new File("" + recod.getLocation());
            boolean deleted = file.delete();
            Log.e("delete", "" + deleted);
        }
    }
}
