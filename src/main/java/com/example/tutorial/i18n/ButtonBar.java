package com.example.tutorial.i18n;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import uk.q3c.krail.core.i18n.I18N;

@TutorialCaption(caption = TutorialLabelKey.News, description = TutorialDescriptionKey.Interesting_Things)
@I18N
public class ButtonBar extends Panel {

    @TutorialCaption(caption = TutorialLabelKey.Yes, description = TutorialDescriptionKey.Yes)
    private Button yesButton;
    @TutorialCaption(caption = TutorialLabelKey.No, description = TutorialDescriptionKey.No)
    private Button noButton;

    public ButtonBar() {
        yesButton = new Button();
        noButton = new Button();
        HorizontalLayout layout = new HorizontalLayout(yesButton, noButton);
        this.setContent(layout);
    }
}