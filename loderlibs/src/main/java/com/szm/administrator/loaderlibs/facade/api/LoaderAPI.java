package com.szm.administrator.loaderlibs.facade.api;

import android.widget.ImageView;

import com.szm.administrator.loaderlibs.cache.api.ImageListener;
import com.szm.administrator.loaderlibs.config.DisplayConfig;

/**
 * 加载图片的api
 * Created by michael on 2016/3/10.
 */
public interface LoaderAPI {

    /**
     * 显示图片到控件上
     * @param imageView
     * @param url
     * @param config
     * @param listener
     */
    void disPlayImage(ImageView imageView, String url, DisplayConfig config, ImageListener listener);

}
