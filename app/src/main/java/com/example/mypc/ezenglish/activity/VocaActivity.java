package com.example.mypc.ezenglish.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.model.VocaData;
import com.example.mypc.ezenglish.util.PrefManager;

import java.io.File;
import java.util.List;

/**
 * Created by MyPC on 21/08/2016.
 */
public class VocaActivity extends Activity {
    ImageView imgthumbnail;
    TextView tv_name, tv_description, tv_tran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemvoca);
        int id = getIntent().getIntExtra("id", 0);
        PrefManager prefManager = new PrefManager(this);
        List<VocaData> list = prefManager.getlistvoca();

        imgthumbnail = (ImageView) findViewById(R.id.thumbnail);
        tv_name = (TextView) findViewById(R.id.name);
        tv_description = (TextView) findViewById(R.id.description);
        tv_tran = (TextView) findViewById(R.id.tran);

        VocaData voca = list.get(id);

        Glide.with(this).load(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + voca.getImg())).into(imgthumbnail);
        tv_name.setText(voca.getName() + "(" + voca.getType() + ")");
        tv_description.setText(voca.getDescription());
        tv_tran.setText(voca.getTrans());

        list.remove(id);
        prefManager.setlistvoca(list);
    }
}
