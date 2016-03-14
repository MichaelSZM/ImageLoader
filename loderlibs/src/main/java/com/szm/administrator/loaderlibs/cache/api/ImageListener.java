package com.szm.administrator.loaderlibs.cache.api;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * 图片加载回调接口
 * Created by michael on 2016/3/10.
 */
public interface ImageListener {

    /**
     * 加载完成时，回调该方法
     * @param imageView
     * @param bitmap
     * @param uri
     */
    void onComplete(ImageView imageView, Bitmap bitmap,String uri);
}
