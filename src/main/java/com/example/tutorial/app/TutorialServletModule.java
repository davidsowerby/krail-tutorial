package com.example.tutorial.app;

import uk.q3c.krail.core.guice.BaseServletModule;

public class TutorialServletModule extends BaseServletModule {

    @Override
    protected void configureServlets() {
        serve("/*").with(TutorialServlet.class);
    }
}