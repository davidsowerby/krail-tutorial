package com.example.tutorial.app;

import org.apache.onami.persist.PersistenceFilter;
import uk.q3c.krail.core.guice.BaseServletModule;

public class TutorialServletModule extends BaseServletModule {

    @Override
    protected void configureServlets() {
        filter("/*").through(PersistenceFilter.class);
        serve("/*").with(TutorialServlet.class);
    }
}