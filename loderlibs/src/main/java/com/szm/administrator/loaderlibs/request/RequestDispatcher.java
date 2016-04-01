package com.szm.administrator.loaderlibs.request;

import android.util.Log;

import com.szm.administrator.loaderlibs.loader.LoaderManager;
import com.szm.administrator.loaderlibs.loader.api.LoaderAPI;

import java.util.concurrent.BlockingQueue;

/**
 * 转发器,不断从请求队列中获取请求处理
 * Created by michael on 2016/3/10.
 */
public class RequestDispatcher extends Thread{

    private BlockingQueue<BitmapRequest> requestQueue;

    public RequestDispatcher(BlockingQueue<BitmapRequest> requestQueue){
        this.requestQueue=requestQueue;
    }

    @Override
    public void run() {
        //非阻塞状态，转发获取的请求
        while(!isInterrupted()){
            //从队列中获取优先级最高的请求处理
            try {
                BitmapRequest bitmapRequest=requestQueue.take();
                Log.i("info--","deal request with serialNO: "+bitmapRequest.getSerialNO()+"------"+bitmapRequest.getImgUri());
                //解析图片地址，获取对象的加载器
                String schema=parseSchema(bitmapRequest.getImgUri());
                Log.i("info--","schema: "+bitmapRequest.getSerialNO()+"------"+schema);
                LoaderAPI loader=LoaderManager.getInstance().getLoader(schema);
                loader.loadBitmap(bitmapRequest);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解析图片地址，获得schema
     * @param imgUri
     * @return
     */
    private String parseSchema(String imgUri) {
        if(imgUri.contains("://")){
            return imgUri.split("://")[0];
        }else{
            Log.i("michael","schema 不符合格式");
        }
        return null;
    }
}
