package com.szm.administrator.loaderlibs.loader.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 获取bitmap时的一些工具方法
 * Created by szm on 2016/4/1.
 */
public class BitmapDeal {

    /**
     * 加载网络图片，这里对bitmap进行了大小压缩和质量的处理
     * @param inputStreams
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeBitmapFromNet(InputStream inputStreams, int reqWidth, int reqHeight){
        Bitmap bitmap = null;
        InputStream inputStream=new BufferedInputStream(inputStreams);
        try {
            inputStream.mark(inputStream.available());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeStream(inputStream, null, options);
        options.inSampleSize=calculateInSampleSize(options,reqWidth,reqHeight);
        try {
            inputStream.reset();
            options.inJustDecodeBounds=false;
            //options.inSampleSize = 2,质量上的处理
            options.inPreferredConfig= Bitmap.Config.RGB_565;
            //当系统内存不足时可以回收Bitmap
            options.inPurgeable=true;
            options.inInputShareable=true;
            bitmap=BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 计算应该缩放的比例因子
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width=options.outWidth;
        int hight=options.outHeight;
        int inSampleSize=1;
        if(width>reqWidth||hight>reqHeight){
            int halfWidth=width/2;
            int halfHight=hight/2;
            while(halfWidth/inSampleSize>reqWidth&&halfHight/inSampleSize>reqHeight){
                inSampleSize*=2;
            }
        }
        return inSampleSize;
    }

}
