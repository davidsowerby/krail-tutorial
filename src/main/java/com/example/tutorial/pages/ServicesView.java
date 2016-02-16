package com.example.tutorial.pages;

import com.example.tutorial.i18n.Caption;
import com.example.tutorial.i18n.DescriptionKey;
import com.example.tutorial.i18n.LabelKey;
import com.example.tutorial.service.ServiceA;
import com.example.tutorial.service.ServiceB;
import com.example.tutorial.service.ServiceC;
import com.example.tutorial.service.ServiceD;
import com.google.inject.Inject;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Listener;
import uk.q3c.krail.core.eventbus.GlobalBus;
import uk.q3c.krail.core.eventbus.SubscribeTo;
import uk.q3c.krail.core.i18n.Translate;
import uk.q3c.krail.core.services.ServiceBusMessage;
import uk.q3c.krail.core.view.Grid3x3ViewBase;
import uk.q3c.krail.core.view.component.ViewChangeBusMessage;

@Listener
@SubscribeTo(GlobalBus.class)
public class ServicesView extends Grid3x3ViewBase {

    private final ServiceC serviceC;
    private final ServiceD serviceD;
    private ServiceA serviceA;
    private ServiceB serviceB;
    @Caption(caption = LabelKey.Start_Service_A, description = DescriptionKey.Start_Service_A)
    private Button startABtn;
    @Caption(caption = LabelKey.Stop_Service_D, description = DescriptionKey.Stop_Service_D)
    private Button stopDBtn;
    @Caption(caption = LabelKey.Stop_Service_C, description = DescriptionKey.Stop_Service_C)
    private Button stopCBtn;
    @Caption(caption = LabelKey.Stop_Service_B, description = DescriptionKey.Stop_Service_B)
    private Button stopBBtn;
    private Translate translate;
    @Caption(caption = LabelKey.State_Changes, description = DescriptionKey.State_Changes)
    private TextArea stateChangeLog;
    @Caption(caption = LabelKey.Clear, description = DescriptionKey.Clear)
    private Button clearBtn;

    @Inject
    protected ServicesView(Translate translate, ServiceA serviceA, ServiceB serviceB, ServiceC serviceC, ServiceD serviceD) {
        super(translate);
        this.translate = translate;
        this.serviceA = serviceA;
        this.serviceB = serviceB;
        this.serviceC = serviceC;
        this.serviceD = serviceD;
    }

    @Override
    protected void doBuild(ViewChangeBusMessage busMessage) {
        super.doBuild(busMessage);
        createButtons();
        createStateMonitor();

    }

    private void createStateMonitor() {
        stateChangeLog = new TextArea();
        stateChangeLog.setSizeFull();
        stateChangeLog.setRows(8);
        getGridLayout().addComponent(stateChangeLog, 0, 1, 2, 1);
        clearBtn = new Button();
        clearBtn.addClickListener(click -> stateChangeLog.clear());
        setBottomCentre(clearBtn);
    }

    @Handler
    protected void handleStateChange(ServiceBusMessage serviceBusMessage) {
        String serviceName = translate.from(serviceBusMessage.getService()
                                                             .getNameKey());
        String logEntry = serviceName + " changed from " + serviceBusMessage.getFromState()
                                                                            .name() + " to " + serviceBusMessage.getToState()
                                                                                                                .name() + ", cause: " +
                serviceBusMessage.getCause();
        String newline = stateChangeLog.getValue()
                                       .isEmpty() ? "" : "\n";
        stateChangeLog.setValue(stateChangeLog.getValue() + newline + logEntry);
    }

    private void createButtons() {
        startABtn = new Button();
        startABtn.addClickListener(click -> serviceA.start());

        stopDBtn = new Button();
        stopDBtn.addClickListener(click -> serviceD.stop());

        stopCBtn = new Button();
        stopCBtn.addClickListener(click -> serviceC.stop());

        stopBBtn = new Button();
        stopBBtn.addClickListener(click -> serviceB.stop());

        Panel panel = new Panel();
        VerticalLayout layout = new VerticalLayout(startABtn, stopDBtn, stopCBtn, stopBBtn);
        panel.setContent(layout);
        setTopLeft(panel);
    }
}