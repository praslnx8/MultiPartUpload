package com.prasilabs.multipartupload.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prasilabs.multipartupload.R;
import com.prasilabs.multipartupload.constants.RequestFor;

/**
 * Created by prasi on 2/2/16.
 */
public class BottomSheet
{

    public static void selectUploadMode(Context context, final SelectUploadCallBack selectUploadCallBack)
    {
        View view = View.inflate(context, R.layout.widget_select_upload_menu, null);

        final Dialog mBottomSheetDialog = new Dialog(context, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView (view);
        mBottomSheetDialog.getWindow ().setLayout (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow ().setGravity (Gravity.BOTTOM);


        TextView asyncImageUpload = (TextView) view.findViewById(R.id.async_select_text);
        TextView volleyImageUpload = (TextView) view.findViewById(R.id.volley_select_task);

        asyncImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mBottomSheetDialog.cancel();
                selectUploadCallBack.uploadType(RequestFor.ASYNC_UPLOAD_REQUEST);
            }
        });

        volleyImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mBottomSheetDialog.cancel();
                selectUploadCallBack.uploadType(RequestFor.VOLLEY_UPLOAD_REQUEST);
            }
        });

        mBottomSheetDialog.show();

    }

    public interface SelectUploadCallBack
    {
        void uploadType(int uploadType);
    }

}
