package com.cide.interactive.kuriolib;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.CompoundButton;

/**
 * Created by dorianchazalon on 06/02/15.
 */
public abstract class TitleImageCheckboxFragmentPagerAdapter extends TitleImageFragmentPagerAdapter {

    public TitleImageCheckboxFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public abstract CompoundButton.OnCheckedChangeListener getListener(int position);
}
