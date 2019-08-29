package com.client;

import com.future.FutureData;
import com.future.RealData;
import com.pojo.Data;

/**
 * @author zxw
 * @date 2019/8/28 20:54
 */
public class Client {
    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();
        return future;
    }

    public static void main(String[] args) {
        Client client = new Client();
        Data name = client.request("name");
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据=" + name.getResult());
    }
}
