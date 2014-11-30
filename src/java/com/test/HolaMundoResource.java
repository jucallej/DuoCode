/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author jcarlos
 */
@Path("HolaMundo")
public class HolaMundoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MyPathResource
     */
    public HolaMundoResource() {
    }


        /**
     * Retrieves representation of an instance of com.test.HolaMundoResource
     * @param factorial
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Path("Factorial/{num}")
    public int Factorial(@PathParam("num") int factorial) {
        /*Integer fact = 1;
        int i = factorial;

        while(i>1){
            fact*=i;
            i--;
        }

        return "Factorial de " + factorial + ": " + fact;*/
        if(factorial < 1)
            return 0;
        else if(factorial == 1)
            return 1;
        else
            return factorial * Factorial(factorial-1);
    }
    @GET
    @Produces("text/json")
    @Path("Factorial")
    public String Factorial() {
        /*Integer fact = 1;
        int i = factorial;

        while(i>1){
            fact*=i;
            i--;
        }

        return "Factorial de " + factorial + ": " + fact;*/
        /*if(factorial < 1)
            return 0;
        else if(factorial == 1)
            return 1;
        else
            return factorial * Factorial(factorial-1);*/
        return "hola mundo";
    }
    
    /**
     * PUT method for updating or creating an instance of HolaMundoResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    }
}
