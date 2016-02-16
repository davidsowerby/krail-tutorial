package com.example.tutorial.pages;

import com.example.tutorial.i18n.LabelKey;
import uk.q3c.krail.core.navigate.sitemap.DirectSitemapModule;
import uk.q3c.krail.core.shiro.PageAccessControl;


public class MyOtherPages extends DirectSitemapModule {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void define() {
        addEntry("private/my-news", MyNews.class, LabelKey.My_News, PageAccessControl.PERMISSION);
        addEntry("ini-config", IniConfigView.class, LabelKey.Ini_Config, PageAccessControl.PUBLIC);
        addEntry("i18n", I18NDemoView.class, LabelKey.I18N, PageAccessControl.PUBLIC);
        addEntry("jpa", JpaView.class, LabelKey.JPA, PageAccessControl.PUBLIC);
        addEntry("jpa/option", JpaOptionView.class, LabelKey.Options, PageAccessControl.PUBLIC);
        addEntry("events", EventsView.class, LabelKey.Events, PageAccessControl.PERMISSION);
        addEntry("services", ServicesView.class, LabelKey.Services, PageAccessControl.PUBLIC);

    }
}