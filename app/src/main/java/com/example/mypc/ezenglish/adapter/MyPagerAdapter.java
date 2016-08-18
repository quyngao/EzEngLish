package com.example.mypc.ezenglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mypc.ezenglish.flagment.Mp3Fragment;
import com.example.mypc.ezenglish.flagment.RecordFragment;
import com.example.mypc.ezenglish.flagment.VocaFlagment;

/**
 * Created by Quylt on 8/18/2016.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 5;

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Mp3Fragment.newInstance();
            case 1:
                return Mp3Fragment.newInstance();
            case 2:
                return RecordFragment.newInstance();
            case 3: // Fragment # 1 - This will show SecondFragment
                return RecordFragment.newInstance();
            case 4: // Fragment # 1 - This will show SecondFragment

                return VocaFlagment.newInstance();
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
