package com.example.mypc.ezenglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mypc.ezenglish.R;
import com.example.mypc.ezenglish.flagment.HistoryFragment;
import com.example.mypc.ezenglish.flagment.Mp3Fragment;
import com.example.mypc.ezenglish.flagment.OneFragment;
import com.example.mypc.ezenglish.flagment.RecordFragment;
import com.example.mypc.ezenglish.flagment.TextFragment;
import com.example.mypc.ezenglish.flagment.VocaFlagment;
import com.example.mypc.ezenglish.model.Lesson;

/**
 * Created by Quylt on 8/18/2016.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 5;
    Lesson l;

    public MyPagerAdapter(FragmentManager fragmentManager, Lesson l) {
        super(fragmentManager);
        this.l = l;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Mp3Fragment.newInstance(l);
            case 1:
                return TextFragment.newInstance(l);
            case 2:
                return VocaFlagment.newInstance(l);
            case 3: // Fragment # 1 - This will show SecondFragment
                return HistoryFragment.newInstance(l);
            // new OneFragment(R.layout.welcome_slide3);
            case 4: // Fragment # 1 - This will show SecondFragment
                return RecordFragment.newInstance(l);
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
