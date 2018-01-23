package com.example.root.mytest;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by root on 23/1/18.
 */

public class GallaryPager extends FragmentPagerAdapter {

    private ArrayList<Uri> uris=new ArrayList<>();

    public GallaryPager(FragmentManager fm, ArrayList<Uri> uris) {
        super(fm);
        this.uris = uris;
    }

    @Override
    public Fragment getItem(int position) {
        return SwipeFragment.newInstance(position,String.valueOf(uris.get(position)));
    }

    @Override
    public int getCount() {
        return uris.size();
    }
}
