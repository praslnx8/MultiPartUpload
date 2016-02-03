package com.prasilabs.multipartupload.pojo;

import com.prasilabs.multipartupload.network.ApiResponse;
import com.prasilabs.multipartupload.utils.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by prasi on 2/2/16.
 */
public class ImagePojo
{
    private String imageUrl;
    private String desc = "no desc";
    private String date = "no date";

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static ArrayList<ImagePojo> getImagePojoList(ApiResponse apiResponse)
    {
        ArrayList<ImagePojo> imagePojoArrayList = new ArrayList<>();

        JSONArray jsonArray = JsonUtil.checkHasArray(apiResponse.getData(), "images");

        if(jsonArray != null && jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    imagePojoArrayList.add(getImagePojo(jsonArray.getJSONObject(i)));
                } catch (JSONException e) {
                }
            }
        }

        return imagePojoArrayList;
    }

    public static ImagePojo getImagePojo(JSONObject jsonObject)
    {
        ImagePojo imagePojo = new ImagePojo();

        imagePojo.setImageUrl(JsonUtil.checkHasString(jsonObject, "picture"));
        imagePojo.setDesc(JsonUtil.checkHasString(jsonObject, "desc"));
        imagePojo.setDate(JsonUtil.checkHasString(jsonObject, "date"));

        return imagePojo;
    }
}
