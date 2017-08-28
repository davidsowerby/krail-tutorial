package com.example.tutorial.pages;

import com.google.inject.Inject;
import uk.q3c.krail.core.view.Grid3x3ViewBase;
import uk.q3c.krail.i18n.Translate;

public class PayrollView extends Grid3x3ViewBase {

    @Inject
    protected PayrollView(Translate translate) {
        super(translate);
    }
}