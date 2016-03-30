package com.szm.administrator.loaderlibs.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.szm.administrator.loaderlibs.cache.api.BitmapCacheAPI;
import com.szm.administrator.loaderlibs.request.BitmapRequest;

/**
 * 内存缓存
 * Created by szm on 2016/3/19.
 */
public class MemoryCache implements BitmapCacheAPI {

    private LruCache<String,Bitmap> lruCache;

    public MemoryCache(){
        //缓存的最大值（可用内存的1/8）
        int maxSize=(int) Runtime.getRuntime().freeMemory()/1024/8;
        lruCache=new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }

    /**
     * 添加到缓存
     *
     * @param request
     * @param bitmap
     */
    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        lruCache.put(request.getImageUriMD5(),bitmap);
    }

    /**
     * 从缓存中获取一个bitmap
     *
     * @param request
     * @return
     */
    @Override
    public Bitmap get(BitmapRequest request) {
        return lruCache.get(request.getImageUriMD5());
    }

    /**
     * 从缓存中移除bitmap
     *
     * @param request
     */
    @Override
    public void remove(BitmapRequest request) {
        lruCache.remove(request.getImageUriMD5());
    }
}
