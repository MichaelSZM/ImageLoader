package com.szm.administrator.loaderlibs.facade.core;

import android.widget.ImageView;

import com.szm.administrator.loaderlibs.cache.api.ImageListener;
import com.szm.administrator.loaderlibs.config.DisplayConfig;
import com.szm.administrator.loaderlibs.config.ImageLoaderConfig;
import com.szm.administrator.loaderlibs.facade.api.LoaderAPI;
import com.szm.administrator.loaderlibs.request.BitmapRequest;
import com.szm.administrator.loaderlibs.request.RequestQueue;

/**
 * 具体的实现类
 * Created by michael on 2016/3/10.
 */
public class SimpleImageLoader implements LoaderAPI {

    private ImageLoaderConfig config;
    private static SimpleImageLoader loader;
    private RequestQueue queue;

    private SimpleImageLoader(ImageLoaderConfig config){
        this.config=config;
        queue=new RequestQueue(config.getThreadCount());
        queue.start();
    }

    public static SimpleImageLoader getInstance(ImageLoaderConfig config){
        if(loader==null){
            synchronized (SimpleImageLoader.class){
                if(loader==null){
                    loader=new SimpleImageLoader(config);
                }
            }
        }
        return loader;
    }

    @Override
    public void disPlayImage(ImageView imageView, String url, DisplayConfig displayConfig, ImageListener listener) {
        queue.addRequest(new BitmapRequest(imageView,url,displayConfig,listener,config));
    }
}
