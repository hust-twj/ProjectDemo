package com.hust_twj.zademo.third_part.event_bus.event;

/**
 * Created by Wenjing.Tang
 * on 2019/1/16
 */
public class FatherEvent implements MyEvent {

    public String msg;

    public FatherEvent(String msg) {
        this.msg = msg;
    }
}
