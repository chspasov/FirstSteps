package com.oauthserver.login;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface LoginInterface {
/**
 * @param customerName
 * @param customerPassword
 * @param number
 * we add the user name and password and send them to Auth.server and the MB server returns to us access point 
 * After that we add mobile number to this auth. client....
 */
    @Path("/addNumber")
    @POST
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    public void CustomerLogin(
            @FormParam("customerName") String customerName,
            @FormParam("customerPassword") String customerPassword,
            @FormParam("number") String number
            );
}
