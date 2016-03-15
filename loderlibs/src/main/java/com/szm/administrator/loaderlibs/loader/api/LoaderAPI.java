package com.szm.administrator.loaderlibs.loader.api;

import com.szm.administrator.loaderlibs.request.BitmapRequest;

/**
 * 加载协议
 * Created by michael on 2016/3/15.
 */
public interface LoaderAPI {

    /**
     * 加载图片
     * @param request
     */
    void loadBitmap(BitmapRequest request);

}
