package com.akaa;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @author zxw
 * @date 2019/8/30 9:44
 */
public class Test {
    public static void main(String[] args) {
        // 第一个参数：系统名称
        // 第二个参数：配置文件
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("samplehello.conf"));
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
        System.out.println("HelloWorld Actor Path:" + a.path());
    }
}
