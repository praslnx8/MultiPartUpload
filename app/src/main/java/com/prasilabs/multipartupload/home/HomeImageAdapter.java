package com.prasilabs.multipartupload.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.prasilabs.multipartupload.R;
import com.prasilabs.multipartupload.network.InternalVolleySingleton;
import com.prasilabs.multipartupload.pojo.ImagePojo;
import com.prasilabs.multipartupload.utils.CommonUtil;

import java.util.ArrayList;

/**
 * Created by prasi on 2/2/16.
 */
public class HomeImageAdapter extends BaseAdapter
{
    private ArrayList<ImagePojo> imagePojos;
    private Context context;

    public HomeImageAdapter(Context context, ArrayList<ImagePojo> imagePojos)
    {
        this.context = context;
        this.imagePojos = imagePojos;
    }

    @Override
    public int getCount()
    {
        return imagePojos.size();
    }

    @Override
    public ImagePojo getItem(int position)
    {
        return imagePojos.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageViewHolder mViewHolder;

        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(this.context);

            convertView = inflater.inflate(R.layout.item_image_view, parent, false);
            mViewHolder = new ImageViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }
        else
        {
            mViewHolder = (ImageViewHolder) convertView.getTag();
        }

        final ImagePojo imagePojo = getItem(position);

        InternalVolleySingleton.loadImage(mViewHolder.imageView, CommonUtil.constructImageUrl(context, imagePojo.getImageUrl()));
        mViewHolder.descView.setText(imagePojo.getDesc());
        mViewHolder.dateView.setText(imagePojo.getDate());
        mViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = CommonUtil.constructImageUrl(context, imagePojo.getImageUrl());
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });

        return convertView;
    }

    private class ImageViewHolder
    {
        NetworkImageView imageView;
        TextView descView, dateView;

        public ImageViewHolder(View itemView)
        {
            imageView = (NetworkImageView) itemView.findViewById(R.id.image_view);
            descView = (TextView) itemView.findViewById(R.id.desc_text);
            dateView = (TextView) itemView.findViewById(R.id.time_text);
        }
    }
}
