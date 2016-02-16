package com.example.tutorial.eventbus;

import uk.q3c.krail.core.eventbus.BusMessage;

public class TutorialMessage implements BusMessage {

    private final String msg;
    private Object sender;

    public TutorialMessage(String msg, Object sender) {
        this.msg = msg;
        this.sender = sender;
    }

    public String getMsg() {
        return msg + " from " + Integer.toHexString(sender.hashCode());
    }
}