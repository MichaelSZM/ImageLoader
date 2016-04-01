package com.szm.administrator.loaderlibs.facade.api;

import android.widget.ImageView;

import com.szm.administrator.loaderlibs.cache.api.ImageListener;

/**
 * 加载图片的api
 * Created by michael on 2016/3/10.
 */
public interface LoaderAPI {


    /**
     * 显示图片到控件上
     * @param imageView
     * @param url
     */
    void displayImage(ImageView imageView, String url);

    /**
     * 显示图片到控件上
     * @param imageView
     * @param url
     * @param listener
     */
    void disPlayImage(ImageView imageView, String url, ImageListener listener);

}
