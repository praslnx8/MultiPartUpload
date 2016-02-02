package com.prasilabs.multipartupload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.prasilabs.multipartupload.constants.RequestFor;
import com.prasilabs.multipartupload.home.HomePagerAdapter;
import com.prasilabs.multipartupload.utils.BottomSheet;
import com.prasilabs.multipartupload.utils.CommonUtil;


public class MainActivity extends AppCompatActivity
{
    private ViewPager viewPager;
    private HomePagerAdapter homePagerAdapter;

    private int mUploadType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        homePagerAdapter = new HomePagerAdapter(MainActivity.this);

        viewPager.setAdapter(homePagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                BottomSheet.selectUploadMode(MainActivity.this, new BottomSheet.SelectUploadCallBack() {
                    @Override
                    public void uploadType(int uploadType)
                    {
                        mUploadType = uploadType;

                        CommonUtil.selectImage(MainActivity.this);
                    }
                });
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED)
        {
            CommonUtil.handleActivityResult(this, requestCode, data, mUploadType);
        }
    }


}
