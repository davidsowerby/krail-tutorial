package com.example.tutorial.i18n;

import static com.example.tutorial.i18n.TutorialMessageKey.*;

public class TutorialMessages_de extends TutorialMessages {
    @Override
    protected void loadMap() {
        put(Banner, "Die Temperatur ist heute {1}. Der CEO hat bemerkt, dass ihre Nachrichten-Kanal {0}");
    }
}
