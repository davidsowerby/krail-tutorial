package com.example.tutorial.i18n;

import uk.q3c.krail.core.persist.clazz.i18n.EnumResourceBundle;

import static com.example.tutorial.i18n.MessageKey.Banner;

public class Messages extends EnumResourceBundle<MessageKey> {

    @Override
    protected void loadMap() {
        put(Banner, "The temperature today is {1}.  The CEO has noticed that her news channel {0}.");
    }
}