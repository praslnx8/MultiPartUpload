package com.prasilabs.multipartupload.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;

import com.prasilabs.multipartupload.R;
import com.prasilabs.multipartupload.async.AsyncTaskFileUpload;
import com.prasilabs.multipartupload.constants.Constants;
import com.prasilabs.multipartupload.constants.RequestFor;
import com.prasilabs.multipartupload.volley.VolleyFileUpload;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by prasi on 1/2/16.
 */
public class CommonUtil
{
    public static  final int TAKE_PHOTO_CODE = 123;

    public static File getTempFile(Context context)
    {
        //it will return /sdcard/image.tmp
        final File path = new File(Environment.getExternalStorageDirectory(), context.getPackageName());
        if (!path.exists()) {
            path.mkdir();
        }
        return new File(path, "image.jpg");
    }

    public static void takePhoto(Activity activity)
    {
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempFile(activity)));
        activity.startActivityForResult(intent, TAKE_PHOTO_CODE);
    }

    public static void selectImage(final Activity activity)
    {
        final int SELECT_FILE = 1;

        final CharSequence[] items = {"Take Photo", "Choose from Gallery", "cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int item)
            {
                if (items[item].equals("Take Photo"))
                {
                    CommonUtil.takePhoto(activity);
                }
                else if (items[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    activity.startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
                } else if (items[item].equals("cancel"))
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public static String constructImageUrl(Context context, String url)
    {
        String baseImageUrl = context.getString(R.string.base_url);

        return baseImageUrl + url;
    }


}
