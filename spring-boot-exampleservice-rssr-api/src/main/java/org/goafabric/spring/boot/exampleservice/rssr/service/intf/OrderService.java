package org.goafabric.spring.boot.exampleservice.rssr.service.intf;

import org.goafabric.spring.boot.exampleservice.rssr.service.intf.dto.Order;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.result.BasicResult;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.result.OrderIdResult;
import org.goafabric.spring.boot.exampleservice.rssr.service.intf.result.OrderResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by amautsch on 26.06.2015.
 */

@Path(OrderService.RESOURCE)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface OrderService {
    String RESOURCE = "/orders";

    /**
     * This method checks the availibility of the service
     *
     * <p/><b>REST Operation:</b><br> GET {@value #RESOURCE}/alive
     * @return A result object containing if the service is available.
     */
    @Path("alive")
    @GET
    BasicResult isAlive();

    /**
     * Retrieve the order by id
     *
     * <p/><b>REST Operation:</b><br> GET {@value #RESOURCE}/{orderId}
     */
    @Path("{orderId}")
    @GET
    OrderResult getOrder(@PathParam("orderId") Long orderId);

    /**
     * Create the order
     *
     * <p/><b>REST Operation:</b><br> POST {@value #RESOURCE}
     */
    @POST
    OrderIdResult createOrder(Order order);

    /**
     * Update the order by id
     *
     * <p/><b>REST Operation:</b><br> PUT {@value #RESOURCE}/{orderId}
     */
    @Path("{orderId}")
    @PUT
    BasicResult updateOrder(@PathParam("orderId") Long orderId, Order order);

    /**
     * Delete the order by id
     *
     * <p/><b>REST Operation:</b><br> DELETE {@value #RESOURCE}/{orderId}
     */
    @Path("{orderId}")
    @DELETE
    BasicResult deleteOrder(@PathParam("orderId") Long orderId);
}
