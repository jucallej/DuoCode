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
import java.util.concurrent.locks.Condition;
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
import mappers.CandidatoMapper;
import mappers.EnvioMapper;
import mappers.FavoritoMapper;
import mappers.Operator;
import mappers.QueryCondition;
import mappers.UsuarioMapper;
import modelo.ErrorYID;
import modelo.IDUsuario;
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
    private EnvioMapper envioMapper;
    private CandidatoMapper candidatoMapper;
    private FavoritoMapper favoritoMapper;

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
        envioMapper = new EnvioMapper(dt);
        candidatoMapper = new CandidatoMapper(dt);
        favoritoMapper = new FavoritoMapper(dt);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Usuarios getUsuario() {
        List<Usuario> usuarios = usuarioMapper.findAll();
        return new Usuarios(usuarios);
    }
    /**
    //Habría que diferenciar los dos Get.
    @GET
    @Path("{correoNick}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorYID getUsuarioId(@PathParam("correoNick") String correoNick) {
        int id = -1;
        List<Usuario> hola = usuarioMapper.findByConditions(new QueryCondition[]{new QueryCondition("correo", Operator.EQ, correoNick)});
        List<Usuario> hola2 = usuarioMapper.findByConditions(new QueryCondition[]{new QueryCondition("nick", Operator.EQ, correoNick)});
        if(hola.size() == 1)
            id = hola.get(0).getId();
        else if(hola2.size()==1)
            id = hola2.get(0).getId();
        return new ErrorYID(id);
    }**/
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorYID newUsuario(Usuario usuario) throws IOException {
        usuario.setId(0);
        usuario.setRol((short)0);
        int nuevoID = usuarioMapper.insert(usuario);
        return new ErrorYID(nuevoID);
    }
    
    
    ////////////////////////////////idUsuario/////////////////////////////////
    
     
    @GET
    @Path("{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuario1(@PathParam("idUsuario") int idUsuario) {
        Usuario usuario = usuarioMapper.findById(idUsuario);
        
        usuario.setHistorialEjercicios(this.envioMapper.getHistorialUsuario(usuario.getId()));
        usuario.setCandidatosPropuestos(this.candidatoMapper.getCandidatosPropuestos(usuario.getId()));
        usuario.setFavoritos(this.favoritoMapper.getEFavoritosDeUnUsuario(usuario.getId()));
        
        return usuario;
    }
    
    
    @DELETE
    @Path("{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Error deleteUsuario(@PathParam("idUsuario") int id){
        String posibleError = "si";
        Usuario aBorrar = usuarioMapper.findById(id);
            if(this.usuarioMapper.delete(aBorrar))
                posibleError = "no";

        return new Error(posibleError);
    }
    
    
}
