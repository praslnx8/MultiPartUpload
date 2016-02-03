package com.prasilabs.multipartupload.model;

import com.prasilabs.multipartupload.network.ApiResponse;
import com.prasilabs.multipartupload.pojo.ImagePojo;
import com.prasilabs.multipartupload.utils.JsonUtil;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by prasi on 2/2/16.
 */
public class ImageModel
{
    private static ImageModel instance = new ImageModel();

    private ArrayList<ImagePojo> imagePojos = new ArrayList<>();

    public static ImageModel getInstance()
    {
        return instance;
    }

    public ArrayList<ImagePojo> getImagesFromServer(ApiResponse apiResponse)
    {
        if(apiResponse != null)
        {
            imagePojos.clear();
            imagePojos.addAll(ImagePojo.getImagePojoList(apiResponse));
        }

        return imagePojos;
    }
}
