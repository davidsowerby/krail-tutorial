package com.example.tutorial.app;

/**
 * Created by David Sowerby on 27 Aug 2017
 */

import com.google.inject.Inject;
import com.google.inject.Singleton;
import uk.q3c.krail.core.guice.BaseServlet;
import uk.q3c.krail.core.ui.ScopedUIProvider;

@Singleton
public class TutorialServlet extends BaseServlet {

    @Inject
    public TutorialServlet(ScopedUIProvider uiProvider) {
        super(uiProvider);
    }

    @Override
    protected String widgetset() {
        return "com.example.tutorial.widgetset.tutorialWidgetset";
    }
}
