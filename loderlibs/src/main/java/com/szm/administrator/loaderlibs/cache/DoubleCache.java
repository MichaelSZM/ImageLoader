package com.szm.administrator.loaderlibs.cache;

import android.content.Context;
import android.graphics.Bitmap;

import com.szm.administrator.loaderlibs.cache.api.BitmapCacheAPI;
import com.szm.administrator.loaderlibs.request.BitmapRequest;

/**
 * 二级缓存
 * Created by michael on 2016/3/10.
 */
public  class DoubleCache implements BitmapCacheAPI {


    /**内存缓存*/
    private MemoryCache memoryCache;
    /**硬盘缓存*/
    private DiskCache diskCache;

    public DoubleCache(Context context){
        memoryCache=new MemoryCache();
        diskCache=DiskCache.getInStance(context);
    }

    /**
     * 添加到缓存
     *
     * @param request
     * @param bitmap
     */
    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        memoryCache.put(request,bitmap);
        diskCache.put(request,bitmap);
    }

    /**
     * 从缓存中获取一个bitmap
     *
     * @param request
     * @return
     */
    @Override
    public Bitmap get(BitmapRequest request) {
        Bitmap bitmap=memoryCache.get(request);
        if(bitmap==null){
            bitmap=diskCache.get(request);
            if(bitmap!=null){
                //放入内存，方便再获取
                memoryCache.put(request,bitmap);
            }
        }
        return bitmap;
    }

    /**
     * 从缓存中移除bitmap
     *
     * @param request
     */
    @Override
    public void remove(BitmapRequest request) {
        memoryCache.remove(request);
        diskCache.remove(request);
    }
}
