package com.prasilabs.multipartupload.network;

import org.json.JSONObject;

/**
 * Created by prasi on 2/2/16.
 */
public class ApiResponse
{
    private boolean status;
    private String message;
    private JSONObject data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
