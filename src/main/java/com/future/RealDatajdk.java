package com.future;

import com.pojo.Data;

import java.util.concurrent.Callable;

/**
 * @author zxw
 * @date 2019/8/28 20:50
 */
public class RealDatajdk implements Callable<String> {
    private String result;

    public RealDatajdk(String result) {
        this.result = result;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(result);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
