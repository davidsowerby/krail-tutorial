package com.example.tutorial.app;


import uk.q3c.krail.core.guice.BaseServletModule;

/**
 * Created by David Sowerby on 27 Aug 2017
 */
public class TutorialServletModule extends BaseServletModule {

    @Override
    protected void configureServlets() {
        serve("/*").with(TutorialServlet.class);
    }
}
