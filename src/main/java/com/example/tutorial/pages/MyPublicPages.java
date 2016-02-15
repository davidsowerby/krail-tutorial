package com.example.tutorial.pages;

import com.example.tutorial.i18n.LabelKey;
import uk.q3c.krail.core.navigate.sitemap.DirectSitemapModule;
import uk.q3c.krail.core.shiro.PageAccessControl;

public class MyPublicPages extends DirectSitemapModule {

    public MyPublicPages() {
        rootURI = "";
    }

    @Override
    protected void define() {
        addEntry("news", NewsView.class, LabelKey.News, PageAccessControl.PUBLIC);
        addEntry("contact-us", ContactUsView.class, LabelKey.Contact_Us, PageAccessControl.PUBLIC);
        addEntry("contact-us/contact-detail", ContactDetailView.class, LabelKey.Contact_Detail, PageAccessControl.PUBLIC, -1);
    }
}