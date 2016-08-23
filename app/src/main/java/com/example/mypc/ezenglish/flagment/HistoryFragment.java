package com.example.mypc.ezenglish.flagment;

import android.content.Context;
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

import java.util.Timer;

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
