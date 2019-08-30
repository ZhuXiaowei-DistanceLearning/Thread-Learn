package com.akaa;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import scala.sys.Prop;

/**
 * @author zxw
 * @date 2019/8/30 9:41
 */
public class HelloWorld extends UntypedActor {
    ActorRef greeter;

    @Override
    public void preStart() throws Exception {
        greeter = getContext().actorOf(Props.create(Geeter.class), "greeter");
        System.out.println("Greeter Actor Path:" + greeter.path());
        greeter.tell(Geeter.Msg.GREET, getSelf());
    }

    @Override
    public void onReceive(Object o) throws Exception {
        if (o == Geeter.Msg.DONE) {
            greeter.tell(Geeter.Msg.DONE, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }
}
