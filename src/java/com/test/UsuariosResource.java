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
@Path("usuarios")
public class UsuariosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuariosResource
     */
    public UsuariosResource() {
    }
    
    @GET
    @Produces("text/json")
    public String getUsuario() {
        
        return "{ \"error\" : \"no\", "
                + "//Si error es no todo ha ido bien, si es si, o tiene otra "
                + "mensaje descriptivo ha habido un error (ej. no existe "
                + "usuario para ese correo/nick lo que sea) "
                + "\"usuario\" : \"localhost/duocode/rest/usuario/idUsuario\" }";
    }
    //Habría que diferenciar los dos Get.
    @GET
    @Path("{idUsuario}")
    public String getUsuarioId() {
        
        return "{ \"error\" : \"no\", //Si error es no todo ha ido bien, si es "
                + "si, o tiene otra mensaje descriptivo ha habido un error (ej. "
                + "no existe usuario para ese correo/nick lo que sea) "
                + "\"usuarios\" : [\"localhost/duocode/rest/usuario/idUsuario1\","
                + " \"localhost/duocode/rest/usuario/idUsuario2\"] }";
    }
    
    @POST
    @Produces("text/json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String newUsuario(@FormParam("json") String Json) throws IOException {
        return "error";
    }
    
    
    ////////////////////////////////idUsuario/////////////////////////////////
    
     
    @GET
    @Path("{idUsuario}")
    @Produces("text/json")
    public String getUsuario1(@PathParam("idUsuario") String idUsuario) {
        //TODO return proper representation object
        return "Devuelve usuario en json";
    }
    
    
    @DELETE
    @Path("{idUsuario}")
    public String deleteUsuario(@PathParam("idUsuario") int id, @FormParam("idUser") String idUser){
        return "{error:no}";
    }
    
    
}
