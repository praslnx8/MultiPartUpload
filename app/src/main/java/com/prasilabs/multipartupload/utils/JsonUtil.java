package com.prasilabs.multipartupload.utils;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by prasi on 2/2/16.
 */
public class JsonUtil {

    private static final String TAG = JsonUtil.class.getSimpleName();

    public static JSONObject createjsonobject(String jsonObjectString)
    {
        try
        {
            return new JSONObject(jsonObjectString);
        }catch (JSONException e){}

        return new JSONObject();
    }

    public static JSONArray createJsonArray(String jsonArrayString)
    {
        try
        {
            return new JSONArray(jsonArrayString);
        }catch (JSONException e){}

        return new JSONArray();
    }

    public static String getStringFromJsonArray(JSONArray jsonArray, int position)
    {
        try
        {
            return String.valueOf(jsonArray.get(position));
        }catch (JSONException e){}

        return "";
    }

    public static String checkHasString(JSONObject jsonObject, String toCheck) {
        if (jsonObject != null) {
            if (jsonObject.has(toCheck)) {
                try {
                    String string = jsonObject.getString(toCheck);
                    if (string.equals("null") || string.equals("Null")) {
                        string = "";
                    }
                    return string;
                } catch (Exception e) {
                    return "";
                }
            } else {
                return "";
            }
        }
        return "";
    }
    public static double checkHasDouble(JSONObject jsonObject, String toCheck) {
        if (jsonObject != null) {
            if (jsonObject.has(toCheck)) {
                try {
                    return jsonObject.getDouble(toCheck);
                } catch (Exception e) {
                    return 0;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }
    public static int checkHasInt(JSONObject jsonObject, String toCheck) {
        if (jsonObject != null) {
            if (jsonObject.has(toCheck)) {
                try {
                    return jsonObject.getInt(toCheck);
                } catch (Exception e) {
                    return 0;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    public static long checkHasLong(JSONObject jsonObject, String toCheck) {
        if (jsonObject != null) {
            if (jsonObject.has(toCheck)) {
                try {
                    return jsonObject.getLong(toCheck);
                } catch (Exception e) {
                    return 0;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    public static boolean checkHasBoolean(JSONObject jsonObject, String toCheck) {
        if (jsonObject != null) {
            if (jsonObject.has(toCheck)) {
                try {
                    return jsonObject.getBoolean(toCheck);
                } catch (JSONException je) {
                    try {
                        return "1".equals(jsonObject.getString(toCheck));
                    } catch (Exception e) {
                    }
                }
            }
        }
        return false;
    }

    public static JSONObject checkHasObject(JSONObject jsonObject, String toCheck) {
        if (jsonObject != null) {
            if (jsonObject.has(toCheck)) {
                try {
                    return jsonObject.getJSONObject(toCheck);
                } catch (Exception e) {
                    return null;
                }
            } else {
                return null;
            }
        }
        return null;
    }
    public static JSONArray checkHasArray(JSONObject jsonObject, String toCheck) {
        if (jsonObject != null) {
            if (jsonObject.has(toCheck)) {
                try {
                    return jsonObject.getJSONArray(toCheck);
                } catch (Exception e) {
                    return null;
                }
            } else {
                return null;
            }
        }
        return null;
    }

    @NonNull
    public static String constructUrl(String url, ArrayMap<String, Object> mParams)
    {
        StringBuilder builder = new StringBuilder();

        if(mParams != null)
        {
            for (String key : mParams.keySet()) {
                String value = String.valueOf(mParams.get(key));
                if (value != null) {
                    try {
                        value = URLEncoder.encode(String.valueOf(value), "UTF-8");


                        if (builder.length() > 0)
                            builder.append("&");
                        builder.append(key).append("=").append(value);
                    } catch (UnsupportedEncodingException e) {
                    }
                }
            }
        }

        if (url.contains("?")) {
            url += "&" + builder.toString();
        } else {
            url += "?" + builder.toString();
        }
        return url;
    }
}
