package com.example.tutorial.pages;

import com.example.tutorial.i18n.TutorialLabelKey;
import uk.q3c.krail.core.navigate.sitemap.DirectSitemapModule;
import uk.q3c.krail.core.shiro.PageAccessControl;


public class MyOtherPages extends DirectSitemapModule {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void define() {
        addEntry("private/my-news", MyNews.class, TutorialLabelKey.My_News, PageAccessControl.PERMISSION);
        addEntry("ini-config", IniConfigView.class, TutorialLabelKey.Ini_Config, PageAccessControl.PUBLIC);
        addEntry("i18n", I18NDemoView.class, TutorialLabelKey.I18N, PageAccessControl.PUBLIC);
    }
}