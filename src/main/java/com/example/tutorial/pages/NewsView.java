package com.example.tutorial.pages;

import com.google.inject.Inject;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import uk.q3c.krail.core.i18n.Translate;
import uk.q3c.krail.core.navigate.Navigator;
import uk.q3c.krail.core.view.Grid3x3ViewBase;
import uk.q3c.krail.core.view.component.ViewChangeBusMessage;

public class NewsView extends Grid3x3ViewBase {

    private Navigator navigator;

    @Inject
    protected NewsView(Translate translate, Navigator navigator) {
        super(translate);
        this.navigator = navigator;
    }

    @Override
    protected void doBuild(ViewChangeBusMessage busMessage) {
        super.doBuild(busMessage);
        Button navigateToContactUsBtn = new Button("Contact Us");
        Button navigateToPrivatePage = new Button("Accounts");
        navigateToContactUsBtn.addClickListener(c -> navigator.navigateTo("contact-us"));
        navigateToPrivatePage.addClickListener(c -> navigator.navigateTo("private/finance-department/accounts"));
        setCentreCell(new VerticalLayout(navigateToContactUsBtn, navigateToPrivatePage));
    }
}