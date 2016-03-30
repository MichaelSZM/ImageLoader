package com.szm.administrator.loaderlibs.cache;

import android.graphics.Bitmap;

import com.szm.administrator.loaderlibs.cache.api.BitmapCacheAPI;
import com.szm.administrator.loaderlibs.request.BitmapRequest;

/**
 * 默认的没有缓存的实现类
 * Created by szm on 2016/3/19.
 */
public class NoCache implements BitmapCacheAPI{
    /**
     * 添加到缓存
     *
     * @param request
     * @param bitmap
     */
    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {

    }

    /**
     * 从缓存中获取一个bitmap
     *
     * @param request
     * @return
     */
    @Override
    public Bitmap get(BitmapRequest request) {
        return null;
    }

    /**
     * 从缓存中移除bitmap
     *
     * @param request
     */
    @Override
    public void remove(BitmapRequest request) {

    }
}
