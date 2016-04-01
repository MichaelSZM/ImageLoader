package com.szm.administrator.loaderlibs.config;

import com.szm.administrator.loaderlibs.cache.NoCache;
import com.szm.administrator.loaderlibs.cache.api.BitmapCacheAPI;
import com.szm.administrator.loaderlibs.policy.SerialPolicy;
import com.szm.administrator.loaderlibs.policy.api.LoadPolicy;

/**
 * imageLoader的相关配置类
 * Created by michael on 2016/3/10.
 */
public class ImageLoaderConfig {

    //缓存策略
    private BitmapCacheAPI bitmapCache;
    //加载策略
    private LoadPolicy loadPolicy;
    //线程数
    private int threadCount=3;
    //图片显示配置
    private DisplayConfig config;

    private ImageLoaderConfig(){
        bitmapCache=new NoCache();
        loadPolicy=new SerialPolicy();
        config=new DisplayConfig();
    };

    /**
     * 构建者，负责创建ImageLoaderConfig对象
     */
    public static class Builder{
        private ImageLoaderConfig imageLoaderConfig;

        public Builder(){
            imageLoaderConfig=new ImageLoaderConfig();
        }

        /**
         * 设置缓存策略
         * @param bitmapCache
         * @return
         */
        public Builder setBitmapCache(BitmapCacheAPI bitmapCache){
            imageLoaderConfig.bitmapCache=bitmapCache;
            return this;
        }

        /**
         * 设置加载策略
         * @param loadPolicy
         * @return
         */
        public Builder setLoadPolicy(LoadPolicy loadPolicy){
            imageLoaderConfig.loadPolicy=loadPolicy;
            return this;
        }

        /**
         * 设置线性数
         * @param count
         * @return
         */
        public Builder setThreadCount(int count ){
            imageLoaderConfig.threadCount=count;
            return this;
        }

        /**
         * 设置正在加载的默认图片
         * @param id
         * @return
         */
        public Builder setLoadingImg(int id){
            imageLoaderConfig.config.loadingImg=id;
            return this;
        }

        /**
         * 设置加载错误的图片
         * @param id
         * @return
         */
        public Builder setErroeImg(int id ){
            imageLoaderConfig.config.errorImg=id;
            return this;
        }

        /**
         * 创建一个ImageLoader配置对象
         * @return
         */
        public ImageLoaderConfig build(){
            return imageLoaderConfig;
        }
    }

    /**
     * 获取缓存策略
     * @return
     */
    public BitmapCacheAPI getBitmapCache() {
        return bitmapCache;
    }

    /**
     * 获取加载策略
     * @return
     */
    public LoadPolicy getLoadPolicy() {
        return loadPolicy;
    }

    /**
     * 获取线程数
     * @return
     */
    public int getThreadCount() {
        return threadCount;
    }

    /**
     * 获取显示配置
     * @return
     */
    public DisplayConfig getConfig() {
        return config;
    }
}
