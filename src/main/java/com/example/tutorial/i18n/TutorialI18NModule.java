package com.example.tutorial.i18n;

import uk.q3c.krail.core.i18n.I18NModule;
import uk.q3c.krail.core.option.InMemory;
import uk.q3c.krail.core.persist.clazz.i18n.ClassPatternSource;

import java.util.Locale;

public class TutorialI18NModule extends I18NModule {

    @Override
    protected void define() {
        defaultLocale(Locale.UK);
        supportedLocales(Locale.GERMANY);
        source(InMemory.class);
        source(ClassPatternSource.class);

    }
}