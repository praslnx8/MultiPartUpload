package com.prasilabs.multipartupload.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by prasi on 2/2/16.
 */
public class HomePagerAdapter extends FragmentPagerAdapter
{

    public HomePagerAdapter(AppCompatActivity activity)
    {
        super(activity.getSupportFragmentManager());
    }

    @Override
    public Fragment getItem(int position)
    {
        if(position == 0)
        {
            return MenuFragment.newInstance();
        }
        else
        {
            return HomeFragment.newInstance();
        }
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Override
    public float getPageWidth(int position)
    {
        /*if (position == 0 || position == getCount())
        {
            return 0.95f;
        }
        return 1f;*/

        if(position == 1) {
            return 0.84f;
        }
        return 1f;
    }
}
