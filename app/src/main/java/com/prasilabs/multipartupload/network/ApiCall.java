package com.prasilabs.multipartupload.network;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.prasilabs.multipartupload.utils.JsonUtil;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

/**
 * Created by prasi on 2/2/16.
 */
public class ApiCall
{
    private static final String TAG = ApiCall.class.getSimpleName();

    public static void callApi(Context context, int urlResourceId, final ArrayMap<String, Object> mParams, final ApiCallBack apiCallBack)
    {
        InternalVolleySingleton volleySingleton = InternalVolleySingleton.getInstance();

        RequestQueue requestQueue = volleySingleton.getRequestQueue();

        StringRequest stringRequest = null;

        String url = context.getString(urlResourceId);

        Response.Listener<String> requestListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Log.d(TAG, "response is: " + response);
                if(apiCallBack != null)
                {
                    ApiResponse apiResponse = new ApiResponse();
                    apiResponse.setStatus(true);
                    JSONObject jsonObject = JsonUtil.createjsonobject(response);
                    apiResponse.setMessage(JsonUtil.checkHasString(jsonObject, "message"));
                    apiResponse.setData(JsonUtil.checkHasObject(jsonObject, "data"));

                    apiCallBack.response(apiResponse);
                }
            }
        };
        //If there is pure network error it will stop overhere itself
        //If it is Success/failure then it will go to OmniController.onReceiveResult()
        Response.ErrorListener errorListener = new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError e)
            {
                if(apiCallBack != null)
                {
                    ApiResponse apiResponse = new ApiResponse();
                    apiResponse.setStatus(false);
                    apiResponse.setMessage(e.getMessage());

                    apiCallBack.response(apiResponse);
                }
            }
        };


        stringRequest = new StringRequest(Request.Method.POST, url, requestListener, errorListener)
        {
            @Override
            protected ArrayMap<String, String> getParams()
            {
                ArrayMap<String, String> mStringParams = new ArrayMap<>();
                for (String key : mParams.keySet())
                {
                    String value = String.valueOf(mParams.get(key));
                    if (!TextUtils.isEmpty(value))
                    {
                        mStringParams.put(key, String.valueOf(value));
                    }
                }
                return mStringParams;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy((int) TimeUnit.SECONDS.toMillis(60), 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

    }

    public interface ApiCallBack
    {
        void response(ApiResponse apiResponse);
    }
}

