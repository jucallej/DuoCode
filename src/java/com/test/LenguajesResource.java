/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jcarlos
 */
@Path("lenguajes")
public class LenguajesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LenguajesResource
     */
    public LenguajesResource() {
    }
    
    @GET
    @Produces("text/html")
    public String getLenguajes() {
        
        return "Devuelve lenguajes";
    }
    
    @POST
    @Produces("text/html")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String newLenguaje(@FormParam("json") String Json) throws IOException {
        return "error";
    }
    
    
}
