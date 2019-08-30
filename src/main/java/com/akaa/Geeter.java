package com.akaa;

import akka.actor.UntypedActor;

/**
 * @author zxw
 * @date 2019/8/30 9:39
 */
public class Geeter extends UntypedActor {

    public static enum Msg {
        GREET, DONE;
    }

    @Override
    public void onReceive(Object o) throws Exception {
        if (o == Msg.GREET) {
            System.out.println("Hello World");
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(o);
        }
    }
}
