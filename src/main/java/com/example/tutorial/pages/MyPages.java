package com.example.tutorial.pages;

import com.example.tutorial.i18n.TutorialLabelKey;
import uk.q3c.krail.core.navigate.sitemap.DirectSitemapModule;
import uk.q3c.krail.core.shiro.PageAccessControl;

public class MyPages extends DirectSitemapModule {

    public MyPages() {
        rootURI = "private/finance";
    }

    @Override
    protected void define() {
        addEntry("", FinanceView.class, TutorialLabelKey.Finance,
                PageAccessControl.PERMISSION);
        addEntry("accounts", AccountsView.class, TutorialLabelKey.Accounts,
                PageAccessControl.PERMISSION);
        addEntry("payroll", PayrollView.class, TutorialLabelKey.Payroll,
                PageAccessControl.PERMISSION);
    }
}