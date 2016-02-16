package com.example.tutorial.service;


import com.example.tutorial.i18n.LabelKey;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import uk.q3c.krail.core.eventbus.GlobalBusProvider;
import uk.q3c.krail.core.i18n.I18NKey;
import uk.q3c.krail.core.i18n.Translate;
import uk.q3c.krail.core.services.AbstractService;
import uk.q3c.krail.core.services.Dependency;
import uk.q3c.krail.core.services.RelatedServicesExecutor;

@Singleton
public class ServiceA extends AbstractService {

    @Dependency(optional = true)
    private ServiceD serviceD;

    @Inject
    protected ServiceA(Translate translate, GlobalBusProvider globalBusProvider, RelatedServicesExecutor servicesExecutor, ServiceD serviceD) {
        super(translate, globalBusProvider, servicesExecutor);
        this.serviceD = serviceD;
    }

    @Override
    protected void doStop() throws Exception {

    }

    @Override
    protected void doStart() throws Exception {

    }

    @Override
    public I18NKey getNameKey() {
        return LabelKey.ServiceA;
    }
}