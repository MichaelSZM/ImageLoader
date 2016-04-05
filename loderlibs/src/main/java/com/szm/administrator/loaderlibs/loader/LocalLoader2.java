package com.szm.administrator.loaderlibs.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.szm.administrator.loaderlibs.loader.utils.BitmapDeal;
import com.szm.administrator.loaderlibs.loader.utils.ImageViewHelper;
import com.szm.administrator.loaderlibs.request.BitmapRequest;

import java.io.File;

/**
 * 加载本地图片的实现类
 * 进行图片的压缩处理
 * Created by michael on 2016/3/15.
 */
public class LocalLoader2 extends AbstractLoader{
    /**
     * 加载图片的方法
     *
     * @param request
     * @return
     */
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        final String path= Uri.parse(request.getImgUri()).getPath();
        File file=new File(path);
        if(!file.exists()){
            return null;
        }
        int sampleSize=BitmapDeal.getZoomFactor(file,ImageViewHelper.getImageViewWidth(request.getImageViewRef()),ImageViewHelper.getImageViewHeight(request.getImageViewRef()));
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=sampleSize;
        options.inPreferredConfig= Bitmap.Config.RGB_565;
        //当系统内存不足时可以回收Bitmap
        options.inPurgeable=true;
        options.inInputShareable=true;
        return BitmapFactory.decodeFile(file.getAbsolutePath(),options);
    }
}
