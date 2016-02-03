package com.prasilabs.multipartupload;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.prasilabs.multipartupload.async.AsyncTaskFileUpload;
import com.prasilabs.multipartupload.constants.Constants;
import com.prasilabs.multipartupload.constants.RequestFor;
import com.prasilabs.multipartupload.home.HomePagerAdapter;
import com.prasilabs.multipartupload.utils.BottomSheet;
import com.prasilabs.multipartupload.utils.CommonUtil;
import com.prasilabs.multipartupload.volley.VolleyFileUpload;

import java.io.File;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity
{
    private ViewPager viewPager;
    private HomePagerAdapter homePagerAdapter;

    private int mUploadType;

    private ProgressDialog progressDialog;

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

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        viewPager.setCurrentItem(2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED)
        {
            handleActivityResult(this, requestCode, data, mUploadType);
        }
    }

    @Override
    public void onBackPressed()
    {
        if(viewPager.getCurrentItem() == 0)
        {
            viewPager.setCurrentItem(1);
        }
        else
        {
            viewPager.setCurrentItem(0);
        }
        //super.onBackPressed();
    }


    private void handleActivityResult(final Context context, int requestCode, Intent data, int uploadType)
    {
        progressDialog.show();

        String picturePath = "";
        String pictureName = "";

        File pictureFile = null;

        if (requestCode == 1)
        {
            Uri selectedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = context.getContentResolver().query(selectedImage, filePath, null, null, null);
            if(c != null && c.getCount() > 0)
            {
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                picturePath = c.getString(columnIndex);
                c.close();

                List<String> items = Arrays.asList(picturePath.split("/"));
                pictureName = items.get(items.size() - 1);

                pictureFile = new File(picturePath);
            }
        }
        else if (requestCode == CommonUtil.TAKE_PHOTO_CODE)
        {
            pictureFile = CommonUtil.getTempFile(context);

            pictureName = pictureFile.getName();
        }


        if(uploadType == RequestFor.VOLLEY_UPLOAD_REQUEST)
        {
            VolleyFileUpload.uploadVolleyImage(context, pictureFile, pictureName, new VolleyFileUpload.VolleyImageUploadCallBack() {
                @Override
                public void uploaded(boolean status, String message)
                {
                    Intent intent = new Intent();
                    intent.setAction(Constants.IMAGE_UPLOAD_SUCCESS_INTENT);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                    progressDialog.dismiss();
                }
            });
        }
        else
        {
            AsyncTaskFileUpload.uploadImage(context, pictureFile, pictureName, new AsyncTaskFileUpload.AsyncImageUploadCallBack() {
                @Override
                public void uploaded(boolean status, String message)
                {
                    Intent intent = new Intent();
                    intent.setAction(Constants.IMAGE_UPLOAD_SUCCESS_INTENT);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                    progressDialog.dismiss();
                }
            });
        }
    }
}
