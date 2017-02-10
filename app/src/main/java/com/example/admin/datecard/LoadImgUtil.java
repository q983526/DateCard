package com.example.admin.datecard;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by admin on 2017/1/23.
 */
public class LoadImgUtil {

    public static void loadImg(String path, Context context, ImageView imageView){
        Glide.with(context).load(path).into(imageView);
    }
}
