package com.n26.test.mvitolo;

import com.google.inject.Module;
import com.n26.test.mvitolo.mocks.MockResourceConfig;
import com.n26.test.mvitolo.resources.TransactionResource;
import com.n26.test.mvitolo.service.TransactionService;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.test.JerseyTest;
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
import java.time.Duration;
import java.time.Instant;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marco on 09/01/2018.
 */
public class TransactionResourceTest extends JerseyTest {

    private static final String TRANSACTIONS_BASE_PATH = "/transactions";

    private HttpServer server;
    private WebTarget target;

    @Mock
    private TransactionService mockTransactionService;

    @Override
    protected Application configure() {
        MockitoAnnotations.initMocks(this);
        MockResourceConfig config = new MockResourceConfig(TransactionResource.class, (Module) binder -> {
            binder.bind(TransactionService.class).toInstance(this.mockTransactionService);
        });
        return config;
    }

    @Before
    public void setUp() throws Exception {
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
    public void createTransactionOK() throws Exception {

        Response response = target.path(TRANSACTIONS_BASE_PATH).request().post(Entity.json(getTransactionsRequestBody()));
        assertEquals("Wrong create transaction HTTP response code.", HttpStatus.CREATED_201.getStatusCode(), response.getStatus());

    }

    @Test
    public void createTransactionKO_OlderThan60() throws Exception {

        Response response = target.path(TRANSACTIONS_BASE_PATH).request().post(Entity.json(getTransactionsRequestKOBody()));
        assertEquals("Wrong create transaction HTTP response code.", HttpStatus.NO_CONTENT_204.getStatusCode(), response.getStatus());

    }

    @Test
    public void createTransactionKO_NoAmoutParameter() throws Exception {

        Response response = target.path(TRANSACTIONS_BASE_PATH).request().post(Entity.json(getTransactionsRequestKONoAmountBody()));
        assertEquals("Wrong create transaction HTTP response code.", HttpStatus.BAD_REQUEST_400.getStatusCode(), response.getStatus());

    }

    @Test
    public void createTransactionKO_NoTimestampParameter() throws Exception {

        Response response = target.path(TRANSACTIONS_BASE_PATH).request().post(Entity.json(getTransactionsRequestKONoTimestampBody()));
        assertEquals("Wrong create transaction HTTP response code.", HttpStatus.BAD_REQUEST_400.getStatusCode(), response.getStatus());

    }

    private String getTransactionsRequestBody() {
        String str =  "{\n" +
                "    \"amount\": 0.11,\n" +
                "    \"timestamp\": " + Instant.now().toEpochMilli() + "\n" +
                "}";
        return str;
    }

    private String getTransactionsRequestKOBody() {
        String str =  "{\n" +
                "    \"amount\": 0.11,\n" +
                "    \"timestamp\": " + Instant.now().minus(Duration.ofMillis(60000)).toEpochMilli() + "\n" +
                "}";
        return str;
    }

    private String getTransactionsRequestKONoAmountBody() {
        String str =  "{\n" +
                "    \"timestamp\": " + Instant.now().minus(Duration.ofMillis(60000)).toEpochMilli() + "\n" +
                "}";
        return str;
    }

    private String getTransactionsRequestKONoTimestampBody() {
        String str =  "{\n" +
                "    \"amount\": 0.11,\n" +
                "}";
        return str;
    }
}
