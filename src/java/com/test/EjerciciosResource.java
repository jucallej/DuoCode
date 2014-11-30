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
@Path("ejercicios")
public class EjerciciosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EjerciciosResource
     */
    public EjerciciosResource() {
    }
    
    @GET
    @Produces("text/html")
    public String getEjercicios() {
        
        return "Devuelve ejercicios";
    }
    
    @POST
    @Produces("text/html")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String newEjercicio(@FormParam("json") String Json) throws IOException {
        return "error";
    }
    
    
    ////////////////////////////////idEjercicio/////////////////////////////////
    
     
    @GET
    @Path("{idEjercicio}")
    @Produces("text/json")
    public String getEjercicio(@PathParam("idEjercicio") String idEjercicio) {
        //TODO return proper representation object
        return "Devuelve Ejercicio";
    }
    
    @PUT
    @Path("{idEjercicio}")
    @Consumes("text/json")
    public String putEjercicio(@PathParam("json") String content) {
        return "{\"error\" : \"no\"}";
    }
    
    @DELETE
    @Path("{idEjercicio}")
    public String deleteEjercicio(@PathParam("idEjercicio") int id, @FormParam("idUser") String idUser){
        return "{error:no}";
    }
    

    
}
