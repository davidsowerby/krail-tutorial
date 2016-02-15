package com.example.tutorial.i18n;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import uk.q3c.krail.core.i18n.I18N;

@Caption(caption = LabelKey.News, description = DescriptionKey.Interesting_Things)
@I18N
public class ButtonBar extends Panel {

    @Caption(caption = LabelKey.Yes, description = DescriptionKey.Yes)
    private Button yesButton;
    @Caption(caption = LabelKey.No, description = DescriptionKey.No)
    private Button noButton;

    public ButtonBar() {
        yesButton = new Button();
        noButton = new Button();

        HorizontalLayout layout = new HorizontalLayout(yesButton, noButton);
        this.setContent(layout);
    }
}