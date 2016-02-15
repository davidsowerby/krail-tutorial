package com.example.tutorial.pages;

import com.google.inject.Inject;
import uk.q3c.krail.core.i18n.Translate;
import uk.q3c.krail.core.view.Grid3x3ViewBase;

public class FinanceView extends Grid3x3ViewBase {

    @Inject
    protected FinanceView(Translate translate) {
        super(translate);
    }
}