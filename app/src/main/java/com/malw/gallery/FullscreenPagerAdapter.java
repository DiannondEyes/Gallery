package com.malw.gallery;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class FullscreenPagerAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<Image> images;
    public FullscreenPagerAdapter(FragmentManager fm, ArrayList<Image> images) {
        super(fm);
        this.images = images;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {return FullscreenFragment.newInstance(images.get(position).getPath());}

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {return images.get(position).getTitle();}
}

