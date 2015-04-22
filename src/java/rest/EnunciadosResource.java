/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import google.ComprobadorGoogle;
import java.beans.PropertyVetoException;
import java.util.Date;
import javax.sql.DataSource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import mappers.EnunciadoMapper;
import mappers.UsuarioMapper;
import modelo.Enunciado;
import modelo.Enunciados;
import modelo.ErrorSimple;
import modelo.ErrorYID;
import utilidades.DatosFijos;
import utilidades.Utilidades;

/**
 * REST Web Service
 *
 * @author jcarlos
 */
@Path("enunciados")
public class EnunciadosResource {

    @Context
    private UriInfo context;
    private EnunciadoMapper enunciadoMapper;
    private UsuarioMapper usuarioMapper;
    
    static private ComboPooledDataSource cpds;

    /**
     * Creates a new instance of EnunciadosResource
     */
    public EnunciadosResource() {
        cpds = Utilidades.checkPoolNull(cpds);

        enunciadoMapper = new EnunciadoMapper(cpds);
        usuarioMapper = new UsuarioMapper(cpds);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Enunciados getEnunciados() {
        return new Enunciados(enunciadoMapper.findAll());
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // {"nombreLenguaje": "Java", "codigo": "codigo del enunciado", "idUsuario": "1", "idDelEjercicioQueResuelve": "3"}
    // hay que tener en la bd al usuario y ej creados
    public ErrorYID newEnunciado(Enunciado enunciado, @HeaderParam("token") String token, @HeaderParam("idUsuario") String idUsuarioGoogle){
        ComprobadorGoogle.getUsuarioAdmin(idUsuarioGoogle, token, usuarioMapper); //Si no es admin ya lanza una expcepcion
        enunciado.setEnunciado(0);
        enunciado.setFechaCreacion(new Date());
        int nuevoID = enunciadoMapper.insert(enunciado);
        return new ErrorYID(nuevoID);
    }
    
    
    ////////////////////////////////idEnunciado/////////////////////////////////
    
     
    @GET
    @Path("{idEnunciado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Enunciado getEnunciado(@PathParam("idEnunciado") int idEnunciado) {
        Enunciado enunciado = enunciadoMapper.findById(idEnunciado);
        if (enunciado == null) throw new WebApplicationException(404);
        return enunciado;
    }
    
    // {"nombreLenguaje": "Java", "codigo": "codigo del enunciado", "idUsuario": "1", "idDelEjercicioQueResuelve": "3"}
    // hay que tener en la bd al usuario y ej creados
    //De nuevo lo modifica correctamente, pero no devuelve Error bien ?????????????
    @PUT
    @Path("{idEnunciado}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ErrorSimple putEnunciado(@PathParam("idEnunciado") int idEnunciado, Enunciado enunciado, @HeaderParam("token") String token, @HeaderParam("idUsuario") String idUsuarioGoogle) {
        ComprobadorGoogle.getUsuarioAdmin(idUsuarioGoogle, token, usuarioMapper); //Si no es admin ya lanza una expcepcion
        Enunciado aModificar = enunciadoMapper.findById(idEnunciado);
        String error = "si";
        if (aModificar != null){
            enunciado.setEnunciado(idEnunciado);
            enunciado.setFechaCreacion(aModificar.getFechaCreacion());
            enunciadoMapper.update(enunciado);
            error = "no";
        }
        
        return new ErrorSimple(error);
    }
    
    @DELETE
    @Path("{idEnunciado}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ErrorSimple deleteEnunciado(@PathParam("idEnunciado") int idEnunciado, @HeaderParam("token") String token, @HeaderParam("idUsuario") String idUsuarioGoogle){
        ComprobadorGoogle.getUsuarioAdmin(idUsuarioGoogle, token, usuarioMapper); //Si no es admin ya lanza una expcepcion
        String posibleError = "si";
        Enunciado aBorrar = enunciadoMapper.findById(idEnunciado);
        if(this.enunciadoMapper.delete(aBorrar))
            posibleError = "no";

        return new ErrorSimple(posibleError);
    }
}
