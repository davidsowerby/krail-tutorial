package com.example.tutorial.app;

import com.example.tutorial.i18n.LabelKey;
import com.example.tutorial.pages.AnnotatedPagesModule;
import com.example.tutorial.pages.MyOtherPages;
import com.example.tutorial.pages.MyPages;
import com.example.tutorial.pages.MyPublicPages;
import com.google.inject.Module;
import uk.q3c.krail.core.config.KrailApplicationConfigurationModule;
import uk.q3c.krail.core.guice.DefaultBindingManager;
import uk.q3c.krail.core.navigate.sitemap.SystemAccountManagementPages;
import uk.q3c.krail.core.sysadmin.SystemAdminPages;
import uk.q3c.krail.core.ui.DefaultUIModule;

import java.util.List;

public class BindingManager extends DefaultBindingManager {

    @Override
    protected Module servletModule() {
        return new TutorialServletModule();
    }

    @Override
    protected void addAppModules(List<Module> modules) {

    }

    @Override
    protected void addSitemapModules(List<Module> baseModules) {
        baseModules.add(new SystemAccountManagementPages());
        baseModules.add(new MyPages().rootURI("private/finance-department"));
        baseModules.add(new AnnotatedPagesModule());
        baseModules.add(new SystemAdminPages());
        baseModules.add(new MyPublicPages());
        baseModules.add(new MyOtherPages());
    }

    @Override
    protected Module uiModule() {
        return new DefaultUIModule().uiClass(TutorialUI.class).applicationTitleKey(LabelKey.Krail_Tutorial);
    }

    @Override
    protected Module applicationConfigurationModule() {
        return new KrailApplicationConfigurationModule()
                .addConfig("moreConfig.ini", 98, false)
                .addConfig("essential.ini", 99, true)
                .addConfig("krail.ini", 100, true);
    }
}
