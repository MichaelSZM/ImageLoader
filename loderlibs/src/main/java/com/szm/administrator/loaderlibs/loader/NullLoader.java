package com.szm.administrator.loaderlibs.loader;

import android.graphics.Bitmap;

import com.szm.administrator.loaderlibs.request.BitmapRequest;

/**
 * 默认没有加载器的情况
 * Created by michael on 2016/3/15.
 */
public class NullLoader extends AbstractLoader
{
    /**
     * 加载图片的方法
     *
     * @param request
     * @return
     */
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        return null;
    }
}
