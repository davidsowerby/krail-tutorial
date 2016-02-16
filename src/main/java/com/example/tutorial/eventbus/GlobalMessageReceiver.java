package com.example.tutorial.eventbus;

import net.engio.mbassy.listener.Listener;
import uk.q3c.krail.core.eventbus.GlobalBus;
import uk.q3c.krail.core.eventbus.SubscribeTo;

@Listener
@SubscribeTo(GlobalBus.class)
public class GlobalMessageReceiver extends MessageReceiver {
}