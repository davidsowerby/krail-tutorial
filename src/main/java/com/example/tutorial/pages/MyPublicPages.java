package com.example.tutorial.pages;

import com.example.tutorial.i18n.TutorialLabelKey;
import uk.q3c.krail.core.navigate.sitemap.DirectSitemapModule;
import uk.q3c.krail.core.shiro.PageAccessControl;

public class MyPublicPages extends DirectSitemapModule {

    public MyPublicPages() {
        rootURI = "";
    }

    @Override
    protected void define() {
        addEntry("news", NewsView.class, TutorialLabelKey.News, PageAccessControl.PUBLIC);
        addEntry("contact-us", ContactUsView.class, TutorialLabelKey.Contact_Us, PageAccessControl.PUBLIC);
        addEntry("contact-us/contact-detail", ContactDetailView.class, TutorialLabelKey.Contact_Detail, PageAccessControl.PUBLIC, -1);
    }
}