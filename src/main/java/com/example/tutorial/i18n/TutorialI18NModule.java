package com.example.tutorial.i18n;

import uk.q3c.krail.core.i18n.KrailI18NModule;
import uk.q3c.krail.i18n.persist.clazz.ClassPatternSource;
import uk.q3c.krail.persist.InMemory;

import java.util.Locale;

public class TutorialI18NModule extends KrailI18NModule {

    @Override
    protected void define() {
        defaultLocale(Locale.UK);
        supportedLocales(Locale.GERMANY);
        source(InMemory.class);
        source(ClassPatternSource.class);

    }
}

