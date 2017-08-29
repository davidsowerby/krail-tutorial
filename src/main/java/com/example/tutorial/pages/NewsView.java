package com.example.tutorial.pages;

import com.example.tutorial.i18n.LabelKey;
import com.google.inject.Inject;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import uk.q3c.krail.core.navigate.Navigator;
import uk.q3c.krail.core.user.notify.UserNotifier;
import uk.q3c.krail.core.view.Grid3x3ViewBase;
import uk.q3c.krail.core.view.component.ViewChangeBusMessage;
import uk.q3c.krail.i18n.Translate;

public class NewsView extends Grid3x3ViewBase {

    private Navigator navigator;
    private UserNotifier userNotifier;

    @Inject
    protected NewsView(Translate translate, Navigator navigator, UserNotifier userNotifier) {
        super(translate);
        this.navigator = navigator;
        this.userNotifier = userNotifier;
    }

    @Override
    protected void doBuild(ViewChangeBusMessage busMessage) {
        super.doBuild(busMessage);
        Button navigateToContactUsBtn = new Button("Contact Us");
        Button navigateToPrivatePage = new Button("Accounts");
        Button sendNotificationBtn = new Button("Send notification");
        navigateToContactUsBtn.addClickListener(c -> navigator.navigateTo("contact-us"));
        navigateToPrivatePage.addClickListener(c -> navigator.navigateTo("finance/accounts"));
        sendNotificationBtn.addClickListener((c -> userNotifier.notifyError(LabelKey.Do_Not_do_That)));
        setCentreCell(new VerticalLayout(navigateToContactUsBtn, navigateToPrivatePage, sendNotificationBtn));
    }
}