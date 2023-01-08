package com.example.restoran.Adapter;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdapterFragmet extends FragmentPagerAdapter {
    Activity activity;
    ArrayList<Fragment> fragments;
    public AdapterFragmet(@NonNull FragmentManager fm, ArrayList<Fragment> fragments, Activity activity) {
        super(fm);
        this.activity = activity;
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
