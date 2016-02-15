package com.example.tutorial.pages;

import com.google.inject.Inject;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import uk.q3c.krail.core.config.ApplicationConfiguration;
import uk.q3c.krail.core.i18n.Translate;
import uk.q3c.krail.core.view.Grid3x3ViewBase;
import uk.q3c.krail.core.view.component.ViewChangeBusMessage;

public class IniConfigView extends Grid3x3ViewBase {

    private ApplicationConfiguration applicationConfiguration;
    private Label tutorialQualityProperty;
    private Label connectionTimeoutProperty;
    private Label tutorialCompletedProperty;

    @Inject
    protected IniConfigView(Translate translate, ApplicationConfiguration applicationConfiguration) {
        super(translate);
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    protected void doBuild(ViewChangeBusMessage busMessage) {
        super.doBuild(busMessage);
        Button showConfigButton = new Button("Show config");
        tutorialQualityProperty = new Label();
        showConfigButton.addClickListener(event -> showConfig());
        setTopCentre(tutorialQualityProperty);
        setMiddleCentre(showConfigButton);
        getGridLayout().setComponentAlignment(tutorialQualityProperty, Alignment.MIDDLE_CENTER);

        connectionTimeoutProperty = new Label();
        tutorialCompletedProperty = new Label();
        setTopRight(tutorialCompletedProperty);
        setTopLeft(connectionTimeoutProperty);
    }

    private void showConfig() {
        tutorialQualityProperty.setValue("Tutorial quality is: " + applicationConfiguration.getString("tutorial.quality"));
        tutorialCompletedProperty.setValue(applicationConfiguration.getString("tutorial.completed"));
        connectionTimeoutProperty.setValue("The timeout is set to: " + applicationConfiguration.getString("connection.timeout"));
    }
}