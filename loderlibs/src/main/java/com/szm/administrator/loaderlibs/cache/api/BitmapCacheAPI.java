package com.szm.administrator.loaderlibs.cache.api;

import android.graphics.Bitmap;

import com.szm.administrator.loaderlibs.request.BitmapRequest;

/**
 * 缓存api
 * Created by Administrator on 2016/3/10.
 */
public interface BitmapCacheAPI {


    /**
     * 添加到缓存
     * @param request
     * @param bitmap
     */
    void put(BitmapRequest request, Bitmap bitmap);


    /**
     * 从缓存中获取一个bitmap
     * @param request
     * @return
     */
    Bitmap get(BitmapRequest request);

    /**
     * 从缓存中移除bitmap
     * @param request
     */
    void remove(BitmapRequest request);

}
