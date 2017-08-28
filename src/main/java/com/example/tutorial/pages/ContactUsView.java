package com.example.tutorial.pages;

import com.google.inject.Inject;
import com.vaadin.ui.Button;
import uk.q3c.krail.core.navigate.NavigationState;
import uk.q3c.krail.core.navigate.Navigator;
import uk.q3c.krail.core.view.Grid3x3ViewBase;
import uk.q3c.krail.core.view.component.ViewChangeBusMessage;
import uk.q3c.krail.i18n.Translate;

public class ContactUsView extends Grid3x3ViewBase {

    private Navigator navigator;

    @Inject
    protected ContactUsView(Translate translate, Navigator navigator) {
        super(translate);
        this.navigator = navigator;
    }

    @Override
    protected void doBuild(ViewChangeBusMessage busMessage) {
        super.doBuild(busMessage);
        Button navigateWithParametersBtn = new Button("Navigate with parameters");
        NavigationState navState = new NavigationState().virtualPage("contact-us/contact-detail")
                .parameter("id", "33")
                .parameter("name", "David");
        navigateWithParametersBtn.addClickListener(c -> navigator.navigateTo(navState));
        setCentreCell(navigateWithParametersBtn);
    }
}