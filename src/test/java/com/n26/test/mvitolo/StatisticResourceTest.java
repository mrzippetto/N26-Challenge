package com.n26.test.mvitolo;

import com.google.inject.Module;
import com.n26.test.mvitolo.mocks.MockResourceConfig;
import com.n26.test.mvitolo.resources.TransactionResource;
import com.n26.test.mvitolo.resources.data.Cache;
import com.n26.test.mvitolo.resources.data.CacheImpl;
import com.n26.test.mvitolo.resources.data.TransactionData;
import com.n26.test.mvitolo.resources.representation.StatisticRepresentation;
import com.n26.test.mvitolo.service.StatisticService;
import com.n26.test.mvitolo.service.TransactionService;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by Marco on 09/01/2018.
 */
public class StatisticResourceTest extends JerseyTest {

    private static final String STATISTICS_BASE_PATH = "/statistics";

    private HttpServer server;
    private WebTarget target;

    @Mock
    private Cache<TransactionData> mockCacheImpl;

    @Override
    protected Application configure() {
        MockitoAnnotations.initMocks(this);
        MockResourceConfig config = new MockResourceConfig(TransactionResource.class, (Module) binder -> {
        });
        return config;
    }

    @Before
    public void setup() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testReadTransactions() throws Exception {

        Response response = target.path(STATISTICS_BASE_PATH).request().get();
        assertEquals("Wrong create transaction HTTP response code.", HttpStatus.OK_200.getStatusCode(), response.getStatus());

        StatisticRepresentation statisticRepresentation = response.readEntity(StatisticRepresentation.class);
        assertNotNull("Wrong read transactions HTTP response entity.", statisticRepresentation);
        assertEquals("Invalid stored value: sum.", 0.0, statisticRepresentation.sum(), 0.0);
        assertEquals("Invalid stored value: avg.", 0.0, statisticRepresentation.avg(), 0.0);
        assertEquals("Invalid stored value: count.", 0, statisticRepresentation.count(), 0.0);
        assertEquals("Invalid stored value: max.", 0.0, statisticRepresentation.max(), 0.0);
        assertEquals("Invalid stored value: min.", 0.0, statisticRepresentation.min(), 0.0);
    }

}
