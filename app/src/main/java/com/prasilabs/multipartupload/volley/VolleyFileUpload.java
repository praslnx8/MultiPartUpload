package com.prasilabs.multipartupload.volley;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by prasi on 1/2/16.
 */
public class VolleyFileUpload
{
    private static final String twoHyphens = "--";
    private static final String lineEnd = "\r\n";
    private static final String boundary = "apiclient-" + System.currentTimeMillis();
    private static final String mimeType = "multipart/form-data;boundary=" + boundary;
    private byte[] multipartBody;

    public static void uploadVolleyImage(Context context, File pictureFile, String pictureName, VolleyImageUploadCallBack volleyImageUploadCallBack)
    {
        VolleyFileUpload volleyFileUpload = new VolleyFileUpload();
        volleyFileUpload.uploadImage(context, pictureFile, pictureName, volleyImageUploadCallBack);
    }

    private VolleyImageUploadCallBack volleyImageUploadCallBack;

    private void uploadImage(final Context context, File pictureFile, String pictureName, VolleyImageUploadCallBack volleyImageUploadCallBack)
    {
        this.volleyImageUploadCallBack = volleyImageUploadCallBack;

        if(!TextUtils.isEmpty(pictureName) && pictureFile != null)
        {
            try {
                int size = (int) pictureFile.length();
                byte[] raw = new byte[size];
                try {
                    BufferedInputStream buf = new BufferedInputStream(new FileInputStream(pictureFile));
                    buf.read(raw, 0, raw.length);
                    buf.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(bos);
                try {
                    buildTextPart(dos, "mparam", "hekki");
                    // the first file
                    buildPart(dos, raw, pictureName);

                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                    // pass to multipart body
                    multipartBody = bos.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String url = "http://192.168.1.23:8080/upload.php";
                MultiPartRequest multipartRequest = new MultiPartRequest(url, null, mimeType, multipartBody, new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response)
                    {
                        String stringResponse = new String(response.data);
                        Toast.makeText(context, "Upload successfully! : " + stringResponse, Toast.LENGTH_LONG).show();
                        Log.d("Response", stringResponse);
                        Log.d("Response", "status: " + response.statusCode + " : " + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Upload failed!\r\n" + error.toString(), Toast.LENGTH_LONG).show();
                        Log.d("Response", error.getMessage());
                        error.printStackTrace();

                    }
                });

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(multipartRequest);

                requestQueue.start();


            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Log.d("xx", "something not right... :( ");
        }
    }


    private void buildPart(DataOutputStream dataOutputStream, byte[] fileData, String fileName) throws IOException {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"upload\"; filename=\""
                + fileName + "\"" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);

        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(fileData);
        int bytesAvailable = fileInputStream.available();

        int maxBufferSize = 1024 * 1024;
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
        byte[] buffer = new byte[bufferSize];

        // read file and write it into form...
        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

        while (bytesRead > 0) {
            dataOutputStream.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }

        dataOutputStream.writeBytes(lineEnd);
    }

    private void buildTextPart(DataOutputStream dataOutputStream, String parameterName, String parameterValue) throws IOException {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + parameterName + "\"" + lineEnd);
        dataOutputStream.writeBytes("Content-Type: text/plain; charset=UTF-8" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);
        dataOutputStream.writeBytes(parameterValue + lineEnd);
    }

    public interface VolleyImageUploadCallBack
    {
        void uploaded(boolean status, String message);
    }
}
