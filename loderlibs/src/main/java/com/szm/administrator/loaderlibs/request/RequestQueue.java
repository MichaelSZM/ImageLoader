package com.szm.administrator.loaderlibs.request;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 维护一个请求队列，和一个转发器数组
 * Created by michael on 2016/3/10.
 */
public class RequestQueue {

    //请求队列
    private BlockingQueue<BitmapRequest> requestQueue=new PriorityBlockingQueue<BitmapRequest>();
    //线程数
    private int threadCount;

    //转发器数组
    private RequestDispatcher[] dispatchers;

    //i++ ++i线程不安全
    //线程安全
    private AtomicInteger ai=new AtomicInteger(0);

    public RequestQueue(int count){
        this.threadCount=count;
    }


    /**
     * 开始转发
     */
    public void start(){
        stop();
        startDispatchers();
    }

    /**
     * 启动所有转发器
     */
    private void startDispatchers() {
        dispatchers=new RequestDispatcher[threadCount];
        for (int i=0;i<threadCount;i++){
            RequestDispatcher dispatcher=new RequestDispatcher(requestQueue);
            dispatchers[i]=dispatcher;
            dispatcher.start();
        }
    }

    /**
     * 停止转发
     */
    public void stop(){
        if(dispatchers!=null&&dispatchers.length>0){
            for (int i=0;i<threadCount;i++){
                dispatchers[i].interrupt();
            }
        }
    }

    /**
     * 添加请求到队列中
     * @param request
     */
    public void addRequest(BitmapRequest request){
        if(!requestQueue.contains(request)){
            request.setSerialNO(ai.incrementAndGet());
            requestQueue.add(request);
            Log.i("info--","add request to queue "+request.getSerialNO());
        }
    }

}
