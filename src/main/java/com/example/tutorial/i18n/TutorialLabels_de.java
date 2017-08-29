package com.example.tutorial.i18n;

import uk.q3c.krail.i18n.EnumResourceBundle;

import static com.example.tutorial.i18n.TutorialLabelKey.*;

public class TutorialLabels_de extends EnumResourceBundle<TutorialLabelKey> {
    @Override
    protected void loadMap() {
        put(is_selected, "aktiviert ist");
        put(is_not_selected, "nicht aktiviert ist");
        put(Options, "die Optionen");
    }
}
