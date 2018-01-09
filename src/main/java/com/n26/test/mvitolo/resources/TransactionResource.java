package com.n26.test.mvitolo.resources;

import com.n26.test.mvitolo.resources.data.TransactionData;
import com.n26.test.mvitolo.resources.representation.TransactionRepresentation;
import com.n26.test.mvitolo.service.TransactionService;
import com.sun.media.sound.InvalidDataException;
import org.apache.log4j.Logger;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Marco on 08/01/2018.
 */
@Path("transactions")
public class TransactionResource {

    final static Logger log = Logger.getLogger(TransactionResource.class);

    @Inject
    private TransactionService transactionService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create( @Nonnull TransactionRepresentation transactionRepresentation ) {
        log.info("Create new transaction: " + transactionRepresentation);

        try {
            transactionService.create( fromRepresentation( transactionRepresentation ) );
        } catch (InvalidDataException e) {
            return Response.noContent().build();
        }
        return Response.created(null).build();
    }

    @Nonnull
    private TransactionData fromRepresentation(TransactionRepresentation transactionRepresentation){

        TransactionData.Builder builder = TransactionData.builder();
        builder.amount( transactionRepresentation.amount());
        builder.timestamp( transactionRepresentation.timestamp() );
        return builder.build();

    }

}
