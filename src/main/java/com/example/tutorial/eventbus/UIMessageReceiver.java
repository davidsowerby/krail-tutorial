package com.example.tutorial.eventbus;

import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Listener;
import uk.q3c.krail.core.eventbus.SubscribeTo;
import uk.q3c.krail.core.eventbus.UIBus;
import uk.q3c.krail.core.push.PushMessage;

@Listener
@SubscribeTo(UIBus.class)
public class UIMessageReceiver extends MessageReceiver {

    @Handler
    public void pushMessage(PushMessage pushMessage) {
        String s = getText();
        getTextField().setValue(s + "\n" + "Push message was originally from: " + pushMessage.getMessage());
    }
}