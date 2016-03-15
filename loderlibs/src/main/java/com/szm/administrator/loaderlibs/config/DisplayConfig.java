package com.szm.administrator.loaderlibs.config;

/**
 * 图片显示设置
 * Created by michael on 2016/3/10.
 */
public class DisplayConfig {

    protected int loadingImg=-1;
    protected int errorImg=-1;

    /**
     * 是否设置正在加载的图片
     * @return
     */
    public boolean hasSetLoadingImg(){
        return loadingImg>0;
    }

    /**
     * 是否设置加载失败的图片
     * @return
     */
    public boolean hasSetErrorgImg(){
        return errorImg>0;
    }

    /**
     * 获取正在加载的图片的id
     * @return
     */
    public int getLoadingImg() {
        return loadingImg;
    }

    /**
     * 获取加载失败的图片的id
     * @return
     */
    public int getErrorImg() {
        return errorImg;
    }
}
