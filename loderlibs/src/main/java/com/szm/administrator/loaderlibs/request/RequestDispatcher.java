package com.szm.administrator.loaderlibs.request;

import android.util.Log;

import java.util.concurrent.BlockingQueue;

/**
 * 转发器
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
                Log.i("info--","deal request with serialNO: "+bitmapRequest.getSerialNO());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
