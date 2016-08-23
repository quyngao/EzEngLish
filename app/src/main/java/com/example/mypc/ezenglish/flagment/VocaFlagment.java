package com.example.mypc.ezenglish.flagment;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.adapter.VocaAdapter;
import com.example.mypc.ezenglish.callback.VocaClick;
import com.example.mypc.ezenglish.dialog.RemidDialog;
import com.example.mypc.ezenglish.model.Lesson;
import com.example.mypc.ezenglish.model.Voca;
import com.example.mypc.ezenglish.model.VocaData;
import com.example.mypc.ezenglish.util.PrefManager;

import java.util.List;
import java.util.Locale;

/**
 * Created by Quylt on 8/18/2016.
 */
public class VocaFlagment extends Fragment implements TextToSpeech.OnInitListener, VocaClick {

    Context context;
    VocaAdapter adapter;
    private TextToSpeech tts;
    PrefManager prefManager;
    RecyclerView recyclerView;
    ImageView imgsetting;
    Lesson l;
    List<VocaData> listvoca;

    public static VocaFlagment newInstance(Lesson l) {
        VocaFlagment fragmentFirst = new VocaFlagment(l);
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    public VocaFlagment(Lesson l) {
        this.l = l;
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.ENGLISH);
            tts.setPitch((float) 1.2);
            tts.setSpeechRate((float) 0.8);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut("Hello Boy");
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    @Override
    public void onPause() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_voca, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        init();
    }

    public void init() {


        prefManager = new PrefManager(context);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        imgsetting = (ImageView) getActivity().findViewById(R.id.img_setredmin);

        imgsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemidDialog reddialog = new RemidDialog(context, l);
                reddialog.show();
            }
        });

        tts = new TextToSpeech(context, this);
        listvoca = prefManager.getlistvoca();
        Log.e("size voca", "" + listvoca.size());
        if (listvoca.size() == 0) {

            for (Voca vc : l.getVocas()) {
                VocaData tmp = new VocaData();
                tmp.copyData(vc);
                listvoca.add(tmp);
            }
            prefManager.setlistvoca(listvoca);
        }
        adapter = new VocaAdapter(context, listvoca, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                speakOut(list.get(i).getName());
//            }
//        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    private void speakOut(String s) {
        tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
    }


    @Override
    public void onItemClick(int location) {
        Toast.makeText(getActivity(), "" + location, Toast.LENGTH_SHORT).show();
        speakOut(listvoca.get(location).getName());
    }

    @Override
    public void onCheckBox(int id, boolean b) {
        Toast.makeText(getActivity(), "" + id + " -- " + b, Toast.LENGTH_SHORT).show();
        listvoca.get(id).setRed(b);
        prefManager.setlistvoca(listvoca);
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
}
