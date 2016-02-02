package com.prasilabs.multipartupload.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etsy.android.grid.StaggeredGridView;
import com.prasilabs.multipartupload.model.ImageModel;
import com.prasilabs.multipartupload.R;
import com.prasilabs.multipartupload.network.ApiCall;
import com.prasilabs.multipartupload.network.ApiResponse;
import com.prasilabs.multipartupload.pojo.ImagePojo;

import java.util.ArrayList;

/**

 */
public class HomeFragment extends Fragment
{
    private View view;
    HomeImageAdapter homeImageAdapter;
    StaggeredGridView gridView;
    private ArrayList<ImagePojo> imagePojos = new ArrayList<>();

    private ImageModel imageModel = ImageModel.getInstance();

    public static HomeFragment newInstance()
    {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        homeImageAdapter = new HomeImageAdapter(getContext(), imagePojos);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if(view == null)
        {
            view = inflater.inflate(R.layout.fragment_home, container, false);

            gridView = (StaggeredGridView) view.findViewById(R.id.grid_view);

            gridView.setAdapter(homeImageAdapter);

            ArrayMap<String, Object> mParams = new ArrayMap<>();
            ApiCall.callApi(getContext(), R.string.get_latest_images_api, mParams, new ApiCall.ApiCallBack() {
                @Override
                public void response(ApiResponse apiResponse)
                {
                    //if(apiResponse.isStatus())
                    {
                        imagePojos.addAll(imageModel.getImagesFromServer(apiResponse));
                        homeImageAdapter.notifyDataSetChanged();
                    }
                   /* else
                    {
                        Toast.makeText(getContext(), apiResponse.getMessage(), Toast.LENGTH_LONG).show();
                    }*/
                }
            });
        }

        return view;
    }
}
