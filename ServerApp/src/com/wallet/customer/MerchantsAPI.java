package com.wallet.customer;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 *  interface for the following operations:
 *      - get user name by merchant id
 *      - create user
 *      - delete user with $0
 *      - rename user
 *      - increase and decrease balance of a user
 *            
 */

@Path("/customer")
@Produces(MediaType.TEXT_PLAIN)
public interface MerchantsAPI {

    @GET
    @Path("/{id}")
    String getMerchantName(@PathParam("id") int id);

    /**
     * Inserts a customer in the database
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createMerchant(@FormParam("name") String name, @FormParam("email") String email,
            @FormParam("country") String country);

    /**
     * @param name
     * deletes a customer from the database
     */
    @DELETE
    @Path("/{id}")
    String deleteMerchant(@PathParam("id") int id);

    /**
     * @param name
     * renames a customer in the database
     */
    @PUT
    @Path("/{id}/rename")
    String renameMerchant(@PathParam("id") int id, @FormParam("name") String name);

    /**
     * @param name 
     * Increases and decreases the balance of a customer from the database
     */
    @PUT
    @Path("/{id}/transaction")
    String paymentsTransaction(@PathParam("id") int id, @FormParam("balance") double balance);

}
