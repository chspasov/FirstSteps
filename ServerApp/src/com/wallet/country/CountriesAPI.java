package com.wallet.country;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wallet.application.Risk;

/**
 * interface for the following operations:
 * - get country's risk level
 * - change country's risk level
 * @see Country
 * @HTTP <code>200 OK</code>
 */

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public interface CountriesAPI {
    /**
     * 
     * @param name
     * @return Risk
     *  Obtain the risk profile for the country by its name
     */
    @GET
    @Path("/countries/{name}")
    Risk getCountriesRisk(@PathParam("name") String name);

    /**
     * 
     * @param name
     * @param riskLevel
     * @return String
     * Change the risk level of the country by its name
     */
    @POST
    @Path("/countries/{name}/change_risk")
    String changeRiskLevel(@PathParam("name") String name, @FormParam("risk") String riskLevel);

}
