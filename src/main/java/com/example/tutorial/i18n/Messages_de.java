package com.example.tutorial.i18n;

import static com.example.tutorial.i18n.MessageKey.Banner;

public class Messages_de extends Messages {
    @Override
    protected void loadMap() {
        put(Banner, "Die Temperatur ist heute {1}. Der CEO hat bemerkt, dass ihre Nachrichten-Kanal {0}");
    }
}
