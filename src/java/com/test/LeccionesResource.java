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
@Path("lecciones")
public class LeccionesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Lecciones
     */
    public LeccionesResource() {
    }

    /*
    { "lecciones": ["localhost/duocode/rest/lecciones/idLeccion1", 
    "localhost/duocode/rest/lecciones/idLeccion2"] }
    */
    @GET
    @Produces("text/html")
    public String getHtml() {
        
        return "Devuelve lecciones";
    }

/*
    {"titulo": "tituloTema", "descripcion": "descripcionTema", 
    "idUsuario": "idDelUsuario", 
    "idEjercicios": ["idEjercicio1", "idEjercicio2"]} ->
    */
    //Se pasa un String por post que sería el json de una leccion.
    @POST
    @Produces("text/html")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String newLeccion(@FormParam("json") String Json) throws IOException {
        return "error";
    }

    
    //////////////////////////////idLeccion/////////////////////////////////////
    
    /*
    { "titulo": "titulo de la leccion (ej. bucles facil)", 
    "descripcion" : "descripción de la leccion (ej. primeros bucles para practicar)", 
    "fechaCreacion": "17/11/2014", 
    "ejercicios": ["localhost/duocode/rest/ejercicios/idEjercicio1", 
    "localhost/duocode/ejercicios/idEjercicio2"] }
    */
    
    @GET
    @Path("{idLeccion}")
    @Produces("text/json")
    public String getLeccion(@PathParam("idLeccion") String idTema) {
        //TODO return proper representation object
        return "Devuelve leccion";
    }
    
    @PUT
    @Path("idLeccion")
    @Consumes("text/json")
    public String putLeccion(String content) {
        return "{\"error\" : \"no\"}";
    }
    
    @DELETE
    @Path("{idLeccion}")
    public String deleteTema(@PathParam("idLeccion") int id, @FormParam("idUser") String idUser){
        return "{error:no}";
    }
    
    
}
