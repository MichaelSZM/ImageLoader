package com.szm.administrator.loaderlibs.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.szm.administrator.loaderlibs.cache.api.BitmapCacheAPI;
import com.szm.administrator.loaderlibs.config.DisplayConfig;
import com.szm.administrator.loaderlibs.loader.api.LoaderAPI;
import com.szm.administrator.loaderlibs.policy.api.LoadPolicy;
import com.szm.administrator.loaderlibs.request.BitmapRequest;

/**
 * 抽象的类，定义加载模版方法
 * Created by michael on 2016/3/15.
 */
public abstract class AbstractLoader implements LoaderAPI {

    //缓存策略
    private BitmapCacheAPI cacheApi;
    //加载策略(倒叙，顺序，优先级)
    private LoadPolicy loadPolicy;
    private DisplayConfig displayConfig;

    /**
     * 加载图片
     *
     * @param request
     */
    @Override
    public void loadBitmap(BitmapRequest request) {

        cacheApi=request.getBitmapCacheAPI();
        loadPolicy=request.getLoadPolicy();
        displayConfig=request.getDisplayConfig();
        //先从缓存中获取
        Bitmap bitmap=cacheApi.get(request);
        if(bitmap==null){
            //显示加载前的图片
            showLoadingImg(request);
            //加载完成再缓存
            bitmap=onLoad(request);
            cacheBitmap(request,bitmap);
        }
        //显示
        deliverToUiThread(request,bitmap);
    }

    /**
     * 在ui线程中显示图片
     * @param request
     * @param bitmap
     */
    private void deliverToUiThread(final BitmapRequest request, final Bitmap bitmap) {
        final ImageView imageView=request.getImageView();
        imageView.post(new Runnable() {
            @Override
            public void run() {
                updateImageview(request, bitmap);
            }
        });
    }


    /**
     * 更新图片
     * @param request
     * @param bitmap
     */
    private void updateImageview(BitmapRequest request, Bitmap bitmap) {
        final ImageView imageView=request.getImageView();
        if(bitmap!=null){//正常显示
            imageView.setImageBitmap(bitmap);
        }
        if(bitmap==null&&displayConfig.hasSetErrorgImg()){//加载失败，显示错误图片
            imageView.setImageResource(displayConfig.getErrorImg());
        }
        if(request.listener!=null){
            request.listener.onComplete(imageView,bitmap,request.getImgUri());
        }

    }

    /**
     * 添加图片到缓存
     * @param request
     * @param bitmap
     */
    private void cacheBitmap(BitmapRequest request, Bitmap bitmap) {
        if(bitmap!=null){
           synchronized (cacheApi){
               cacheApi.put(request,bitmap);
           }
        }

    }

    /**
     * 加载图片的方法
     * @param request
     * @return
     */
    protected abstract Bitmap onLoad(BitmapRequest request);

    /**
     * 显示正在加载图片
     * @param request
     */
    private void showLoadingImg(BitmapRequest request){
        if(displayConfig!=null&&displayConfig.hasSetLoadingImg()){
            final ImageView imageView=request.getImageView();
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(displayConfig.getLoadingImg());
                }
            });
        }
    }


}
