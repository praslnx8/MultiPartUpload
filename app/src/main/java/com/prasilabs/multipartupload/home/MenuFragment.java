package com.prasilabs.multipartupload.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prasilabs.multipartupload.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends android.support.v4.app.Fragment
{
    private View view;

    public static MenuFragment newInstance()
    {
        MenuFragment menuFragment = new MenuFragment();

        return menuFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if(view == null)
        {
            view = inflater.inflate(R.layout.fragment_menu, container, false);


        }
        return view;
    }

}
