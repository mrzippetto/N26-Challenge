package com.n26.test.mvitolo.features;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.n26.test.mvitolo.resources.data.Cache;
import com.n26.test.mvitolo.resources.data.CacheImpl;
import com.n26.test.mvitolo.resources.data.TransactionData;
import com.n26.test.mvitolo.service.StatisticService;
import com.n26.test.mvitolo.service.impl.StatisticServiceImpl;
import com.n26.test.mvitolo.service.TransactionService;
import com.n26.test.mvitolo.service.impl.TransactionServiceImpl;
import org.apache.log4j.Logger;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.ServiceLocatorProvider;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Singleton;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

/**
 * Created by Marco on 07/01/2018.
 */
public class GuiceFeature implements Feature {

    final static Logger log = Logger.getLogger(GuiceFeature.class);

    @Override
    public boolean configure(FeatureContext context) {
        ServiceLocator serviceLocator = ServiceLocatorProvider.getServiceLocator(context);

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);

        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                log.info("Bind all common application injections with Guice.");
                bind(new TypeLiteral<Cache<TransactionData>>() {}).to(CacheImpl.class).in(Singleton.class);
                bind(TransactionService.class).to(TransactionServiceImpl.class).in(Singleton.class);
                bind(StatisticService.class).to(StatisticServiceImpl.class).in(Singleton.class);
            }
        });
        guiceBridge.bridgeGuiceInjector(injector);

        return true;
    }
}
