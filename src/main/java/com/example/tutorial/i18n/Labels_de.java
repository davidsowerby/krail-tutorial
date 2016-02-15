package com.example.tutorial.i18n;

import static com.example.tutorial.i18n.LabelKey.*;

public class Labels_de extends Labels {
    @Override
    protected void loadMap() {
        put(is_selected, "aktiviert ist");
        put(is_not_selected, "nicht aktiviert ist");
        put(Options, "die Optionen");
    }
}