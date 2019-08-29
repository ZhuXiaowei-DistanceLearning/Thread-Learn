package com.future;

import com.pojo.Data;

/**
 * @author zxw
 * @date 2019/8/28 20:49
 */
public class FutureData implements Data {
    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    public synchronized String getRealData() {
        if (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;
    }

    @Override
    public String getResult() {
        return realData.getResult();
    }
}
