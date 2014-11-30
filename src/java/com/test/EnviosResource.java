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
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author jcarlos
 */
@Path("envios")
public class EnviosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EnviosResource
     */
    public EnviosResource() {
    }

    
     
    @GET
    @Produces("text/json")
    public String getEnvios() {
        
        return "Lista envios en json";
    }
    
    
    @PUT
    @Consumes("text/json")
    public String putEnvio(@PathParam("json") String content) {
        //content: {"ejercicio" : "localhost/duocode/rest/ejercicios/idEjercicio1", 
        //"idLenguajeOrigien": "1", "idLenguajeDestino": "2", 
        //"codigo": "codigo enviado en el lenguaje de destino", 
        //"idUsuario" : "idDelUsuario"}
        return "{\"error\" : \"no\"}";
    }
}
