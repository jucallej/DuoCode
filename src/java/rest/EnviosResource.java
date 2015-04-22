/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import puntuador.*;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import google.ComprobadorGoogle;
import java.beans.PropertyVetoException;
import java.util.Date;
import javax.sql.DataSource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import mappers.EnvioMapper;
import mappers.UsuarioMapper;
import modelo.Envio;
import modelo.Envios;
import modelo.ErrorYPuntuacion;
import modelo.Usuario;
import utilidades.DatosFijos;
import utilidades.Utilidades;

/**
 * REST Web Service
 *
 * @author jcarlos
 */
@Path("envios")
public class EnviosResource {

    @Context
    private UriInfo context;
    private EnvioMapper envioMapper;
    private UsuarioMapper usuarioMapper;
    
    static private ComboPooledDataSource cpds;

    /**
     * Creates a new instance of EnviosResource
     */
    public EnviosResource() {
        cpds = Utilidades.checkPoolNull(cpds);
        
        envioMapper = new EnvioMapper(cpds);
        usuarioMapper = new UsuarioMapper(cpds);
    }

    
     
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Envios getEnvios(@HeaderParam("token") String token, @HeaderParam("idUsuario") String idUsuarioGoogle) {
        ComprobadorGoogle.getUsuarioAdmin(idUsuarioGoogle, token, usuarioMapper); //Si no es admin ya lanza una expcepcion
        return new Envios(this.envioMapper.findAll());
    }
    
    /*
    La primer vez funciona, despues no te deja  y deberia
    {"codigo":"codigo en java","idUsuario":1,"idEjercicio":3,"lenguajeOrigen":"Java","lenguajeDestino":"C++"}
    */
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorYPuntuacion putEnvio(Envio envio, @HeaderParam("token") String token, @HeaderParam("idUsuario") String idUsuarioGoogle){
        Usuario usuario = ComprobadorGoogle.getUsuario(idUsuarioGoogle, token, usuarioMapper); //Si no es admin ya lanza una expcepcion
        if (usuario.getId() != envio.getIdUsuario()) throw new WebApplicationException(401);
        envio.setId(0);
        envio.setFecha(new Date());
        Puntuador.puntuar(envio);
        int id = this.envioMapper.insert(envio);
        if (id == -1)
            envio.setPuntuacion(-1);
        
        return new ErrorYPuntuacion(envio.getPuntuacion(), id);
    }
}
