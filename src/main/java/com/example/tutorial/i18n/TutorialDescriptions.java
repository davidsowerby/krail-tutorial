package com.example.tutorial.i18n;

import uk.q3c.krail.i18n.EnumResourceBundle;

import static com.example.tutorial.i18n.TutorialDescriptionKey.*;

public class TutorialDescriptions extends EnumResourceBundle<TutorialDescriptionKey> {
    @Override
    protected void loadMap() {
        put(Interesting_Things, "Interesting things that have happened in the world.");
        put(Yes, "Press for Yes");
        put(No, "Press for No");
    }
}