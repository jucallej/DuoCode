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
@Path("candidatos")
public class CandidatosResource {
/////////////////////////////////////
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CandidatosResource
     */
    public CandidatosResource() {
    }

      @GET 
    @Produces("text/html")
    public String getCandidatos() {
        
        return "Devuelve Candidatos";
    }
    
    @POST
    @Produces("text/html")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String newCandidato(@FormParam("json") String Json) throws IOException {
        return "error";
    }
    
    
    ////////////////////////////////idCandidato/////////////////////////////////
    
     
    @GET
    @Path("{idCandidato}")
    @Produces("text/json")
    public String getCandidato(@PathParam("idCandidato") String idCandidato) {
        //TODO return proper representation object
        return "Devuelve Candidato";
    }
    
    @PUT
    @Path("{idCandidato}")
    @Consumes("text/json")
    public String putCandidato(@PathParam("json") String content) {
        return "{\"error\" : \"no\"}";
    }
    
    @DELETE
    @Path("{idCandidato}")
    public String deleteCandidato(@PathParam("idCandidato") int id, @FormParam("idUser") String idUser){
        return "{error:no}";
    }
}
