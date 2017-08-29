package com.example.tutorial.i18n;

import uk.q3c.krail.i18n.EnumResourceBundle;

import static com.example.tutorial.i18n.TutorialMessageKey.*;

public class TutorialMessages extends EnumResourceBundle<TutorialMessageKey> {

    @Override
    protected void loadMap() {
        put(Banner, "The temperature today is {1}.  The CEO has noticed that her news channel {0}.");
    }
}