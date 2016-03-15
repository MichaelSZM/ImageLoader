package com.szm.administrator.loaderlibs.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.szm.administrator.loaderlibs.request.BitmapRequest;

import java.io.File;

/**
 * 加载本地图片的实现类
 * Created by michael on 2016/3/15.
 */
public class LocalLoader extends AbstractLoader{
    /**
     * 加载图片的方法
     *
     * @param request
     * @return
     */
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        String path= Uri.parse(request.getImgUri()).getPath();
        File file=new File(path);
        if(file.exists()){
            return BitmapFactory.decodeFile(path);
        }
        return null;
    }
}
