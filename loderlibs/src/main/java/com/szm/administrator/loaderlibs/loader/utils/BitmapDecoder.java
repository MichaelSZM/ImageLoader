package com.szm.administrator.loaderlibs.loader.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by szm on 2016/4/2.
 */
public abstract class BitmapDecoder {

    public Bitmap decodeBitmap(int width,int height){
        //1.初始化Options
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        //获取图片实际宽高
        decodeBitmapWithOption(options);
        //计算缩放因子
        calculateSampleSizeWithOption(options,width,height);
        return decodeBitmapWithOption(options);
    }

    private void calculateSampleSizeWithOption(BitmapFactory.Options options, int reqWidth, int reqHeight) {
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
        //全景图
        //当inSampleSize为2，图片的宽与高变成原来的1/2
        //options.inSampleSize = 2
        options.inSampleSize = inSampleSize;

        //每个像素2个字节
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        //Bitmap占用内存
        options.inJustDecodeBounds = false;
        //当系统内存不足时可以回收Bitmap
        options.inPurgeable = true;
        options.inInputShareable = true;
    }

    /**
     * 这里2次调用该方法，最后一次获取bitmap，第一次仅仅获取图片的实际宽高
     * @param options
     * @return
     */
    protected abstract Bitmap decodeBitmapWithOption(BitmapFactory.Options options);

}
