package com.szm.administrator.loaderlibs.request;

import android.widget.ImageView;

import com.szm.administrator.loaderlibs.cache.api.BitmapCacheAPI;
import com.szm.administrator.loaderlibs.cache.api.ImageListener;
import com.szm.administrator.loaderlibs.config.DisplayConfig;
import com.szm.administrator.loaderlibs.config.ImageLoaderConfig;
import com.szm.administrator.loaderlibs.policy.api.LoadPolicy;

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

    public BitmapRequest(ImageView imageView, String uri, DisplayConfig displayConfig, ImageListener imageListener, ImageLoaderConfig config){
        loadPolicy=config.getLoadPolicy();
        bitmapCacheAPI=config.getBitmapCache();
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
        return bitmapCacheAPI != null ? bitmapCacheAPI.equals(that.bitmapCacheAPI) : that.bitmapCacheAPI == null;

    }

    @Override
    public int hashCode() {
        int result = serialNO;
        result = 31 * result + (loadPolicy != null ? loadPolicy.hashCode() : 0);
        result = 31 * result + (bitmapCacheAPI != null ? bitmapCacheAPI.hashCode() : 0);
        return result;
    }
}