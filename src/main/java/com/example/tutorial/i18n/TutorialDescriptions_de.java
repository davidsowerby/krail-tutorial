package com.example.tutorial.i18n;

import static com.example.tutorial.i18n.TutorialDescriptionKey.*;

public class TutorialDescriptions_de extends TutorialDescriptions {
    @Override
    protected void loadMap() {
        put(Interesting_Things, "Interessante Dinge, die in der Welt haben geschehen");
        put(You_just_asked_for_a_pay_increase, "Sie haben für eine Lohnerhöhung gebeten");
        put(Yes, "Drücken Sie für Ja");
        put(No, "Drücken Sie für Nein");
    }
}
