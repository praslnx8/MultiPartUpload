package com.prasilabs.multipartupload.pojo;

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
}
