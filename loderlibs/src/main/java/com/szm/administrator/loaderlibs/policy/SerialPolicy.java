package com.szm.administrator.loaderlibs.policy;

import com.szm.administrator.loaderlibs.policy.api.LoadPolicy;
import com.szm.administrator.loaderlibs.request.BitmapRequest;

/**
 * fifo,先后顺序
 * Created by michael on 2016/3/10.
 */
public class SerialPolicy implements LoadPolicy {


    @Override
    public int compareTo(BitmapRequest request1, BitmapRequest request2) {
        return request1.getSerialNO()-request2.getSerialNO();
    }
}
