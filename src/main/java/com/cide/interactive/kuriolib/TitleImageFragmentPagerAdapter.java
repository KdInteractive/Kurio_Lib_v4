package com.cide.interactive.kuriolib;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by leehack on 08/01/15.
 */
public abstract class TitleImageFragmentPagerAdapter extends FragmentPagerAdapter {

    public TitleImageFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public abstract Drawable getTitleImage(int position);

    public abstract boolean getEnableState(int position);
}
