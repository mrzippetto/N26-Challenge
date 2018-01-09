package com.n26.test.mvitolo.mocks;

import com.google.inject.Module;
import com.n26.test.mvitolo.features.GuiceFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class MockResourceConfig extends ResourceConfig {

    public MockResourceConfig(Class<?> resourceClass, Module... guiceModules) {
        this(new Class[] {resourceClass}, guiceModules);
    }

    public MockResourceConfig(Class<?>[] resourceClasses, Module... guiceModules) {
        super(resourceClasses);

        register(new GuiceFeature());
        register(JacksonFeature.class);
    }
}
