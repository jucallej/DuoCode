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
import javax.ws.rs.DELETE;
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
@Path("enunciados")
public class EnunciadosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EnunciadosResource
     */
    public EnunciadosResource() {
    }

      @GET
    @Produces("text/html")
    public String getEnunciados() {
        
        return "Devuelve enunciados";
    }
    
    @POST
    @Produces("text/html")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String newEnunciado(@FormParam("json") String Json) throws IOException {
        return "error";
    }
    
    
    ////////////////////////////////idEnunciado/////////////////////////////////
    
     
    @GET
    @Path("{idEnunciado}")
    @Produces("text/json")
    public String getEnunciado(@PathParam("idEnunciado") String idEnunciado) {
        //TODO return proper representation object
        return "Devuelve Enunciado";
    }
    
    @PUT
    @Path("{idEnunciado}")
    @Consumes("text/json")
    public String putEnunciado(@PathParam("json") String content) {
        return "{\"error\" : \"no\"}";
    }
    
    @DELETE
    @Path("{idEnunciado}")
    public String deleteEnunciado(@PathParam("idEnunciado") int id, @FormParam("idUser") String idUser){
        return "{error:no}";
    }
}
