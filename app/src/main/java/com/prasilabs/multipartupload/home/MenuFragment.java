package com.prasilabs.multipartupload.home;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prasilabs.multipartupload.R;

import org.w3c.dom.Text;

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

            Toolbar toolbar = (Toolbar) view.findViewById(R.id.tool_bar);
            final TextView githubBtn = (TextView) view.findViewById(R.id.github_btn);
            final TextView shareBtn = (TextView) view.findViewById(R.id.share_btn);
            final TextView pageBtn = (TextView) view.findViewById(R.id.page_btn);
            final TextView exitBtn = (TextView) view.findViewById(R.id.exit_btn);

            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(v.equals(githubBtn))
                    {
                        String url = "http://praslnx8.github.com";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                    else if(v.equals(shareBtn))
                    {
                        final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                    }
                    else if(v.equals(pageBtn))
                    {
                        String url = "http://www.facebook.com/prasilabs";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                    else if(v.equals(exitBtn))
                    {
                        getActivity().finish();
                    }
                }
            };

            githubBtn.setOnClickListener(onClickListener);
            shareBtn.setOnClickListener(onClickListener);
            pageBtn.setOnClickListener(onClickListener);
            exitBtn.setOnClickListener(onClickListener);

        }
        return view;
    }

}
