package com.example.tutorial.pages;

import com.google.inject.Inject;
import uk.q3c.krail.core.view.Grid3x3ViewBase;
import uk.q3c.krail.i18n.Translate;

public class FinanceView extends Grid3x3ViewBase {

    @Inject
    protected FinanceView(Translate translate) {
        super(translate);
    }
}