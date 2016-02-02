package com.prasilabs.multipartupload.model;

import com.prasilabs.multipartupload.network.ApiResponse;
import com.prasilabs.multipartupload.pojo.ImagePojo;

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
        ImagePojo imagePojo = new ImagePojo();
        imagePojo.setImageUrl("https://lh4.ggpht.com/wKrDLLmmxjfRG2-E-k5L5BUuHWpCOe4lWRF7oVs1Gzdn5e5yvr8fj-ORTlBF43U47yI=w300");
        imagePojos.add(imagePojo);

        ImagePojo imagePojospl = new ImagePojo();
        imagePojo.setImageUrl("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQocRLxAFYjAsRKg8MaB5kHC7kaet_XY31TSRYk56MiroPS69dd");
        imagePojos.add(imagePojospl);

        ImagePojo imagePojo1 = new ImagePojo();
        imagePojo.setImageUrl("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTR3PWMMycCZSYhuvGrBLdX4wMmr2iaaT0RIPbOurqfSGGrdWcf");
        imagePojos.add(imagePojo1);

        ImagePojo imagePojo2 = new ImagePojo();
        imagePojo.setImageUrl("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQL9OYxsRm9_Sp3ocE40jfGAU3RhF915xEFo3ELiLalFQcRG7Im");
        imagePojos.add(imagePojo2);

        ImagePojo imagePoj3 = new ImagePojo();
        imagePojo.setImageUrl("https://lh4.ggpht.com/wKrDLLmmxjfRG2-E-k5L5BUuHWpCOe4lWRF7oVs1Gzdn5e5yvr8fj-ORTlBF43U47yI=w300");
        imagePojos.add(imagePoj3);

        ImagePojo imagePoj4 = new ImagePojo();
        imagePojo.setImageUrl("https://www.raspberrypi.org/wp-content/uploads/2015/08/raspberry-pi-logo.png");
        imagePojos.add(imagePoj4);

        ImagePojo imagePojo5 = new ImagePojo();
        imagePojo.setImageUrl("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQL9OYxsRm9_Sp3ocE40jfGAU3RhF915xEFo3ELiLalFQcRG7Im");
        imagePojos.add(imagePojo5);

        return imagePojos;
    }
}
