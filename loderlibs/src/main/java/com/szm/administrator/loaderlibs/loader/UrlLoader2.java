package com.szm.administrator.loaderlibs.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.szm.administrator.loaderlibs.request.BitmapRequest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 加载网络图片的实现类
 * Created by michael on 2016/3/15.
 */
public class UrlLoader2 extends AbstractLoader{
    /**
     * 加载图片的方法
     *
     * @param request
     * @return
     */
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        HttpURLConnection connection=null;
        InputStream in=null;
        try {
            connection= (HttpURLConnection) new URL(request.getImgUri()).openConnection();
            in=new BufferedInputStream(connection.getInputStream());
            return BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                connection.disconnect();
            }
        }
        return null;
    }

}
