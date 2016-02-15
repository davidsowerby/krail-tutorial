package com.example.tutorial.pages;

import com.example.tutorial.i18n.LabelKey;
import uk.q3c.krail.core.navigate.sitemap.AnnotationSitemapModule;

public class AnnotatedPagesModule extends AnnotationSitemapModule {

    @Override
    protected void define() {
        addEntry("com.example.tutorial.pages", LabelKey.Accounts);
    }
}