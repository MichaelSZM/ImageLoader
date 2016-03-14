package com.szm.administrator.loaderlibs.policy.api;

import com.szm.administrator.loaderlibs.request.BitmapRequest;

/**
 * 加载策略api
 * Created by michael on 2016/3/10.
 */
public interface LoadPolicy {

    /**
     * 2个bitmapRequest进行比较
     * @param request1
     * @param request2
     * @return 小于0 request1<request2
     */
    int compareTo(BitmapRequest request1,BitmapRequest request2);

}
