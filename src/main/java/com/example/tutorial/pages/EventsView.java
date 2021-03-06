package com.example.tutorial.pages;

import com.example.tutorial.eventbus.*;
import com.example.tutorial.i18n.Caption;
import com.example.tutorial.i18n.DescriptionKey;
import com.example.tutorial.i18n.LabelKey;
import com.google.inject.Inject;
import com.vaadin.ui.Button;
import uk.q3c.krail.core.eventbus.GlobalBusProvider;
import uk.q3c.krail.core.eventbus.SessionBusProvider;
import uk.q3c.krail.core.eventbus.UIBusProvider;
import uk.q3c.krail.core.i18n.Translate;
import uk.q3c.krail.core.push.Broadcaster;
import uk.q3c.krail.core.view.Grid3x3ViewBase;
import uk.q3c.krail.core.view.component.ViewChangeBusMessage;

public class EventsView extends Grid3x3ViewBase {
    private final UIBusProvider uiBusProvider;
    private final GlobalBusProvider globalBusProvider;
    private Broadcaster broadcaster;
    @Caption(caption = LabelKey.Singleton, description = DescriptionKey.Singleton)
    private Button singletonSendBtn;
    @Caption(caption = LabelKey.Session, description = DescriptionKey.Session)
    private Button sessionSendBtn;
    @Caption(caption = LabelKey.UI, description = DescriptionKey.UI)
    private Button uiSendBtn;
    private SessionBusProvider sessionBusProvider;
    private GlobalMessageReceiver singletonMessageReceiver;
    private MessageReceiver sessionMessageReceiver;
    private MessageReceiver uiMessageReceiver;


    @Inject
    protected EventsView(Translate translate, UIBusProvider uiBusProvider, SessionBusProvider sessionBusProvider, GlobalBusProvider globalBusProvider,
                         GlobalMessageReceiver singletonMessageReceiver, SessionMessageReceiver sessionMessageReceiver, UIMessageReceiver uiMessageReceiver,
                         Broadcaster broadcaster) {
        super(translate);
        this.uiBusProvider = uiBusProvider;
        this.sessionBusProvider = sessionBusProvider;
        this.singletonMessageReceiver = singletonMessageReceiver;
        this.sessionMessageReceiver = sessionMessageReceiver;
        this.uiMessageReceiver = uiMessageReceiver;
        this.globalBusProvider = globalBusProvider;
        this.broadcaster = broadcaster;
    }

    @Override
    protected void doBuild(ViewChangeBusMessage busMessage) {
        super.doBuild(busMessage);
        constructEventSendButtons();
        layoutReceivers();
    }

    private void layoutReceivers() {
        setTopCentre(singletonMessageReceiver);
        setMiddleCentre(sessionMessageReceiver);
        setBottomCentre(uiMessageReceiver);
    }

    private void constructEventSendButtons() {
        singletonSendBtn = new Button();
        sessionSendBtn = new Button();
        uiSendBtn = new Button();
        singletonSendBtn.addClickListener(click -> {
            String m = "Singleton";
            globalBusProvider.get()
                             .publish(new TutorialMessage(m, this));
            broadcaster.broadcast("refresh", m);

        });
        sessionSendBtn.addClickListener(click -> {
            String m = "Session";
            sessionBusProvider.get()
                              .publish(new TutorialMessage(m, this));
            broadcaster.broadcast("refresh", m);
        });
        uiSendBtn.addClickListener(click -> {
            String m = "UI";
            uiBusProvider.get()
                         .publish(new TutorialMessage(m, this));
            broadcaster.broadcast("refresh", m);
        });
        setTopLeft(singletonSendBtn);
        setMiddleLeft(sessionSendBtn);
        setBottomLeft(uiSendBtn);
    }
}

