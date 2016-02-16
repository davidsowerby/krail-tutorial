package com.example.tutorial.app;

import com.google.inject.Inject;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.converter.ConverterFactory;
import com.vaadin.server.ErrorHandler;
import uk.q3c.krail.core.i18n.CurrentLocale;
import uk.q3c.krail.core.i18n.I18NProcessor;
import uk.q3c.krail.core.i18n.Translate;
import uk.q3c.krail.core.navigate.Navigator;
import uk.q3c.krail.core.option.Option;
import uk.q3c.krail.core.push.Broadcaster;
import uk.q3c.krail.core.push.PushMessageRouter;
import uk.q3c.krail.core.ui.ApplicationTitle;
import uk.q3c.krail.core.ui.DefaultApplicationUI;
import uk.q3c.krail.core.user.notify.VaadinNotification;
import uk.q3c.krail.core.view.component.*;

@Theme("valo")
@Push
public class TutorialUI extends DefaultApplicationUI {

    @Inject
    protected TutorialUI(Navigator navigator, ErrorHandler errorHandler, ConverterFactory converterFactory, ApplicationLogo logo, ApplicationHeader header,
                         UserStatusPanel userStatusPanel, UserNavigationMenu menu, UserNavigationTree navTree, Breadcrumb breadcrumb, SubPagePanel subpage,
                         MessageBar messageBar, Broadcaster broadcaster, PushMessageRouter pushMessageRouter, ApplicationTitle applicationTitle, Translate
                                     translate, CurrentLocale currentLocale, I18NProcessor translator, LocaleSelector localeSelector, VaadinNotification
                                     vaadinNotification, Option option) {
        super(navigator, errorHandler, converterFactory, logo, header, userStatusPanel, menu, navTree, breadcrumb, subpage, messageBar, broadcaster,
                pushMessageRouter, applicationTitle, translate, currentLocale, translator, localeSelector, vaadinNotification, option);
    }
}