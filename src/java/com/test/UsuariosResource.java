/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.List;
import javax.sql.DataSource;
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
import mappers.UsuarioMapper;
import modelo.Usuario;
import modelo.Usuarios;
import utilidades.DatosFijos;

/**
 * REST Web Service
 *
 * @author Johana
 */
@Path("usuarios")
public class UsuariosResource {
    
    @Context
    private UriInfo context;
    private UsuarioMapper usuarioMapper;

    /**
     * Creates a new instance of UsuariosResource
     */
    public UsuariosResource() {
        DataSource dt = null;
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        
        try {
                cpds.setDriverClass("org.gjt.mm.mysql.Driver");
        } catch (PropertyVetoException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        cpds.setJdbcUrl(DatosFijos.JdbcUrl);
        cpds.setUser(DatosFijos.USER);
        cpds.setPassword(DatosFijos.PASS);
        cpds.setAcquireRetryAttempts(DatosFijos.AcquireRetryAttempts);
        cpds.setAcquireRetryDelay(DatosFijos.AcquireRetryDelay);
        cpds.setBreakAfterAcquireFailure(DatosFijos.BreakAfterAcquireFailure);
        dt = cpds;
        
        usuarioMapper = new UsuarioMapper(dt);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Usuarios getUsuario() {
        List<Usuario> usuarios = usuarioMapper.findAll();
        return new Usuarios(usuarios);
    }
    //Habría que diferenciar los dos Get.
    @GET
    @Path("{correoNick}")
    public String getUsuarioId(@PathParam("correoNick") String correoNick) {
        
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
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuario1(@PathParam("idUsuario") int idUsuario) {
        //TODO return proper representation object
        return usuarioMapper.findById(idUsuario);
    }
    
    
    @DELETE
    @Path("{idUsuario}")
    public String deleteUsuario(@PathParam("idUsuario") int id, @FormParam("idUser") String idUser){
        return "{error:no}";
    }
    
    
}
