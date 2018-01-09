package com.n26.test.mvitolo.resources;

import com.n26.test.mvitolo.exception.ElaborateStatisticException;
import com.n26.test.mvitolo.resources.representation.StatisticRepresentation;
import com.n26.test.mvitolo.service.StatisticService;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Marco on 08/01/2018.
 */
@Path("statistics")
public class StatisticResource {

    final static Logger log = Logger.getLogger(StatisticResource.class);

    @Inject
    private StatisticService statisticService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readStatistics() {
        log.info("Read all statistics.");

        StatisticRepresentation representation = statisticService.elaborate();

        return Response.ok(representation).build();
    }
}
