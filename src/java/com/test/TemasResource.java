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
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author jcarlos
 */
@Path("temas")
public class TemasResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DuocodeResource
     */
    public TemasResource() {
    }

    /**
     * Retrieves representation of an instance of com.test.TemasResource
     * @return an instance of java.lang.String
     * 
     * { "temas": ["localhost/duocode/rest/temas/idTema1", 
     * "localhost/duocode/rest/temas/idTema2"] }
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        //TODO return proper representation object
        return "Devuelve temas";
    }

    
    
/* {"titulo": "tituloTema", 
    "descripcion": "descripcionTema", 
    "idUsuario": "idDelUsuario", 
    "idLecciones": ["idLeccion1", "idLeccion2"]} ->*/
    @POST
    @Produces("text/html")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newTodo(@FormParam("titulo") String titulo,
            @FormParam("descripcion") String descripcion,
            @FormParam("idUser") String idUser,
            @FormParam("idLecciones") String idLecciones) throws IOException {
        
    }

    
    /////////////////////////////IDTEMA/////////////////////////////////////
   /*
    { "titulo": "titulo del tema (ej. bucles)", 
    "descripcion" : "descripción del tema (ej. los bucles hacen bla bla)", 
    "fechaCreacion": "17/11/2014", "lecciones": ["localhost/duocode/rest/lecciones/idLeccion1", 
    "localhost/duocode/rest/lecciones/idLeccion2"] }
    */
    
    @GET
    @Path("{idTema}")
    @Produces("text/json")
    public String getTema(@PathParam("idTema") String idTema) {
        //TODO return proper representation object
        return "Devuelve tema";
    }
    
    
    /*
    {"titulo": "tituloTema", 
    "descripcion": "descripcionTema", 
    "idUsuario": "idDelUsuario", 
    "idLecciones": ["idLeccion1", "idLeccion2"]}
    */
    @PUT
    @Path("idTema")
    @Consumes("text/json")
    public String putTema(String content) {
        return "{\"error\" : \"no\"}";
    }
    
    @DELETE
    @Path("{idTema}")
    public String deleteTema(@PathParam("idTema") int id, @FormParam("idUser") String idUser){
        return "{error:no}";
    }
    
}
