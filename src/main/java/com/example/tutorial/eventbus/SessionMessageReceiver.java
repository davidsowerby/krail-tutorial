package com.example.tutorial.eventbus;

import net.engio.mbassy.listener.Listener;
import uk.q3c.krail.core.eventbus.SessionBus;
import uk.q3c.krail.core.eventbus.SubscribeTo;

@Listener
@SubscribeTo(SessionBus.class)
public class SessionMessageReceiver extends MessageReceiver {

}