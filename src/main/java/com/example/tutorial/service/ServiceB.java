package com.example.tutorial.service;


import com.example.tutorial.i18n.LabelKey;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import uk.q3c.krail.core.eventbus.GlobalBusProvider;
import uk.q3c.krail.core.i18n.I18NKey;
import uk.q3c.krail.core.i18n.Translate;
import uk.q3c.krail.core.services.AbstractService;
import uk.q3c.krail.core.services.RelatedServicesExecutor;

@Singleton
public class ServiceB extends AbstractService {

    @Inject
    protected ServiceB(Translate translate, GlobalBusProvider globalBusProvider, RelatedServicesExecutor servicesExecutor) {
        super(translate, globalBusProvider, servicesExecutor);
    }

    @Override
    protected void doStop() throws Exception {

    }

    @Override
    protected void doStart() throws Exception {

    }

    @Override
    public I18NKey getNameKey() {
        return LabelKey.ServiceB;
    }
}