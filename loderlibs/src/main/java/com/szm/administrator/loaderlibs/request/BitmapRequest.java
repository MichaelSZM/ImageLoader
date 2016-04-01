package com.szm.administrator.loaderlibs.request;

import android.widget.ImageView;

import com.szm.administrator.loaderlibs.cache.api.BitmapCacheAPI;
import com.szm.administrator.loaderlibs.cache.api.ImageListener;
import com.szm.administrator.loaderlibs.config.DisplayConfig;
import com.szm.administrator.loaderlibs.config.ImageLoaderConfig;
import com.szm.administrator.loaderlibs.policy.api.LoadPolicy;
import com.szm.administrator.loaderlibs.utils.MD5Utils;

import java.lang.ref.SoftReference;

/**
 * 请求对象,实现Comparable需要重写equals和hashcode方法
 * Created by michael on 2016/3/10.
 */
public class BitmapRequest implements Comparable<BitmapRequest>{

    //编号
    private int serialNO;
    //加载策略
    private LoadPolicy loadPolicy;
    //缓存策略
    private BitmapCacheAPI bitmapCacheAPI;
    //默认显示的图片
    private DisplayConfig displayConfig;
    //显示控件
    //当系统内存不足时，把引用的对象进行回收
    private SoftReference<ImageView> imageViewRef;
    //监听回调
    public ImageListener listener;
    //图片路径
    private String imgUri;
    //加密后的路径
    private String imageUriMD5;


    public BitmapRequest(ImageView imageView, String uri, ImageListener imageListener, ImageLoaderConfig config){
        loadPolicy=config.getLoadPolicy();
        bitmapCacheAPI=config.getBitmapCache();
        if(config.getConfig()!=null){
            this.displayConfig=config.getConfig();
        }
        this.imageViewRef =new SoftReference<>(imageView);
        listener=imageListener;
        imgUri=uri;
        imageUriMD5= MD5Utils.toMD5(uri);
        //解决快速滑动图片错位
        imageView.setTag(imgUri);
    }

    /**
     * 设置编号
     * @param serialNO
     */
    public void setSerialNO(int serialNO) {
        this.serialNO = serialNO;
    }

    /**
     * 获取编号
     * @return
     */
    public int getSerialNO() {
        return serialNO;
    }

    /**
     * 获取使用的加载策略
     * @return
     */
    public LoadPolicy getLoadPolicy() {
        return loadPolicy;
    }

    /**
     * 获取使用的缓存策略
     * @return
     */
    public BitmapCacheAPI getBitmapCacheAPI() {
        return bitmapCacheAPI;
    }

    /**
     * 获取默认显示的配置对象
     * @return
     */
    public DisplayConfig getDisplayConfig() {
        return displayConfig;
    }

    /**
     * 获取显示控件
     * @return
     */
    public ImageView getImageViewRef() {
        if(imageViewRef!=null){
            return imageViewRef.get();
        }
        return null;
    }

    /**
     * 获取图片路径
     * @return
     */
    public String getImgUri() {
        return imgUri;
    }

    /**
     * 获取加密后的路径
     * @return
     */
    public String getImageUriMD5() {
        return imageUriMD5;
    }

    @Override
    public int compareTo(BitmapRequest another) {
        return loadPolicy.compareTo(this,another);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitmapRequest that = (BitmapRequest) o;

        if (serialNO != that.serialNO) return false;
        if (loadPolicy != null ? !loadPolicy.equals(that.loadPolicy) : that.loadPolicy != null)
            return false;
        if (bitmapCacheAPI != null ? !bitmapCacheAPI.equals(that.bitmapCacheAPI) : that.bitmapCacheAPI != null)
            return false;
        if (displayConfig != null ? !displayConfig.equals(that.displayConfig) : that.displayConfig != null)
            return false;
        if (imageViewRef != null ? !imageViewRef.equals(that.imageViewRef) : that.imageViewRef != null)
            return false;
        if (listener != null ? !listener.equals(that.listener) : that.listener != null)
            return false;
        if (imgUri != null ? !imgUri.equals(that.imgUri) : that.imgUri != null) return false;
        return imageUriMD5 != null ? imageUriMD5.equals(that.imageUriMD5) : that.imageUriMD5 == null;

    }

    @Override
    public int hashCode() {
        int result = serialNO;
        result = 31 * result + (loadPolicy != null ? loadPolicy.hashCode() : 0);
        result = 31 * result + (bitmapCacheAPI != null ? bitmapCacheAPI.hashCode() : 0);
        return result;
    }
}
