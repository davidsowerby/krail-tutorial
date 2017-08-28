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
    }
}