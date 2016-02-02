package com.prasilabs.multipartupload.network;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.prasilabs.multipartupload.MyApp;

/**
 * Created by prasi on 2/2/16.
 */
public class InternalVolleySingleton
{
    private static InternalVolleySingleton sInstance = null;
    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;

    private InternalVolleySingleton()
    {
        mRequestQueue = Volley.newRequestQueue(MyApp.getAppContext());

        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            LruCache<String, Bitmap> cache = new LruCache<>((int) (Runtime.getRuntime().maxMemory() / 1024) / 8);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static InternalVolleySingleton getInstance()
    {
        if (sInstance == null) {
            sInstance = new InternalVolleySingleton();
        }
        return sInstance;
    }

    public static void loadImage(NetworkImageView networkImageView, String url)
    {
       networkImageView.setImageUrl(url, getInstance().mImageLoader);
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
