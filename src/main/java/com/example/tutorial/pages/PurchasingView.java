package com.example.tutorial.pages;

import com.google.inject.Inject;
import uk.q3c.krail.core.navigate.sitemap.View;
import uk.q3c.krail.core.shiro.PageAccessControl;
import uk.q3c.krail.core.view.Grid3x3ViewBase;
import uk.q3c.krail.i18n.Translate;

@View(uri = "finance/purchasing", pageAccessControl = PageAccessControl.PERMISSION, labelKeyName = "Purchasing")
public class PurchasingView extends Grid3x3ViewBase {

    @Inject
    protected PurchasingView(Translate translate) {
        super(translate);
    }
}