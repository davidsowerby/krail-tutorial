package com.example.tutorial.eventbus;

import net.engio.mbassy.listener.Listener;
import uk.q3c.krail.core.eventbus.SubscribeTo;
import uk.q3c.krail.core.eventbus.UIBus;

@Listener
@SubscribeTo(UIBus.class)
public class UIMessageReceiver extends MessageReceiver {
}