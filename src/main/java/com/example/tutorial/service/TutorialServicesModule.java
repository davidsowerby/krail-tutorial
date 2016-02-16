package com.example.tutorial.service;

import com.example.tutorial.i18n.LabelKey;
import uk.q3c.krail.core.services.AbstractServiceModule;
import uk.q3c.krail.core.services.Dependency;

public class TutorialServicesModule extends AbstractServiceModule {

    @Override
    protected void registerServices() {
        registerService(LabelKey.ServiceA, ServiceA.class);
        registerService(LabelKey.ServiceB, ServiceB.class);
        registerService(LabelKey.ServiceC, ServiceC.class);
        registerService(LabelKey.ServiceD, ServiceD.class);
    }

    @Override
    protected void defineDependencies() {
        addDependency(LabelKey.ServiceA, LabelKey.ServiceB, Dependency.Type.ALWAYS_REQUIRED);
        addDependency(LabelKey.ServiceA, LabelKey.ServiceC, Dependency.Type.REQUIRED_ONLY_AT_START);
    }
}