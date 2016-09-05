package com.example.mypc.ezenglish.flagment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.adapter.HistoryAdapter;
import com.example.mypc.ezenglish.model.History;
import com.example.mypc.ezenglish.model.Lesson;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

import io.realm.RealmList;

/**
 * Created by Quylt on 8/22/2016.
 */
public class HistoryFragment extends Fragment {
    Context mcontext;
    ListView listview;
    RealmList<History> list;
    Lesson l;
    HistoryAdapter historyAdapter = null;

    public static HistoryFragment newInstance(Lesson l) {
        HistoryFragment fragmentFirst = new HistoryFragment(l);
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    public HistoryFragment(Lesson l) {
        this.l = l;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mcontext = getActivity();
        init();
    }

    public void init() {
        getViews();
    }

    public void getViews() {
        listview = (ListView) getActivity().findViewById(R.id.listhisto);


        BarChart mBarChart = (BarChart) getActivity().findViewById(R.id.barchart);

        mBarChart.addBar(new BarModel("Mon", 4.3f, Color.parseColor("#3F51B5")));
        mBarChart.addBar(new BarModel("Tue", 0.3f, Color.parseColor("#3F51B5")));
        mBarChart.addBar(new BarModel("Wen", 1.1f, Color.parseColor("#3F51B5")));
        mBarChart.addBar(new BarModel("Thu", 5.5f, Color.parseColor("#3F51B5")));
        mBarChart.addBar(new BarModel("Fri", 2.3f, Color.parseColor("#3F51B5")));
        mBarChart.addBar(new BarModel("Sat", 4.3f, Color.parseColor("#3F51B5")));
        mBarChart.addBar(new BarModel("Sun", 6.3f, Color.parseColor("#3F51B5")));

        mBarChart.startAnimation();


        PieChart mPieChart = (PieChart) getActivity().findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel("Audio", 10, Color.parseColor("#3F51B5")));
        mPieChart.addPieSlice(new PieModel("Voca", 20, Color.parseColor("#20d2bb")));
        mPieChart.addPieSlice(new PieModel("Story", 60, Color.parseColor("#f64c73")));
        mPieChart.addPieSlice(new PieModel("Other", 8, Color.parseColor("#FFEB3B")));


        mPieChart.startAnimation();

        Log.e("ok history", "" + l.getHistories().size() + " id : " + l.getId());
        list = l.getHistories();
        for (int i = 0; i < list.size(); i++) {
            History h = list.get(i);
            Log.e("item history", "type " + h.getType() + "time " + h.getTimes() + "long " + h.getLongs());
        }
        historyAdapter = new HistoryAdapter(mcontext, R.layout.list_history, list);
        listview.setAdapter(historyAdapter);
        listview.setFastScrollEnabled(true);


    }
}
