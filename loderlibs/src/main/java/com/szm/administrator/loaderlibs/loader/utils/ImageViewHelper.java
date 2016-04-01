package com.szm.administrator.loaderlibs.loader.utils;

import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 帮助获取imageView的宽高
 * Created by szm on 2016/4/2.
 */
public class ImageViewHelper {

    //默认的图片宽高
    private static int DEFAULT_WIDTH = 200;
    private static int DEFAULT_HEIGHT = 200;

    /**
     * 获取imageView的宽度
     * @param imageView
     * @return
     */
    public static int getImageViewWidth(ImageView imageView){
        if(imageView!=null){
            int width=0;
            ViewGroup.LayoutParams params=imageView.getLayoutParams();
            if(params!=null&&params.width!= ViewGroup.LayoutParams.WRAP_CONTENT){
                width=imageView.getWidth();
            }
            if(width<=0&&params!=null){
                width=params.width;
            }
//            if(width<=0){
//                width=getImageFieldValue(imageView,"mMaxWidth");
//            }
            return width;
        }
        return DEFAULT_WIDTH;
    }

    public static int getImageViewHeight(ImageView imageView){
        if(imageView!=null){
            int height=0;
            ViewGroup.LayoutParams params=imageView.getLayoutParams();
            if(params!=null&&params.width!= ViewGroup.LayoutParams.WRAP_CONTENT){
                height=imageView.getHeight();
            }
            if(height<=0&&params!=null){
                height=params.height;
            }
//            if(height<=0){
//                height=getImageFieldValue(imageView,"mMaxHeight");
//            }
            return height;
        }
        return DEFAULT_HEIGHT;
    }

    /**
     * 获取ImageView中的某个属性的值
     * @param imageView
     * @param mMaxHeight
     * @return
     */
//    private static int getImageFieldValue(ImageView imageView, String mMaxHeight) {
//        try {
//            Field field=imageView.getClass().getDeclaredField(mMaxHeight);
//            field.setAccessible(true);
////            int value=(Integer)field.get(imageView);
//            if(value>0&&value<Integer.MAX_VALUE){
//                return value;
//            }
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }

}
