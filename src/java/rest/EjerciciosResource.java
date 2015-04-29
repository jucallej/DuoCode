/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import autentificacion.ComprobadorAutenticidad;
import java.beans.PropertyVetoException;
import java.util.List;
import javax.sql.DataSource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import mappers.EjercicioMapper;
import mappers.EnunciadoMapper;
import mappers.UsuarioMapper;
import modelo.Ejercicio;
import modelo.Ejercicios;
import modelo.Enunciados;
import modelo.ErrorSimple;
import modelo.ErrorYID;
import modelo.IDUsuario;
import utilidades.Comprobadores;
import utilidades.DatosFijos;
import utilidades.Utilidades;

/**
 * REST Web Service
 *
 * @author jcarlos
 */
@Path("ejercicios")
public class EjerciciosResource {

    @Context
    private UriInfo context;
    private EjercicioMapper ejercicioMapper;
    private EnunciadoMapper enunciadoMapper;
    private UsuarioMapper usuarioMapper;
    
    static private ComboPooledDataSource cpds;

    /**
     * Creates a new instance of EjerciciosResource
     */
    public EjerciciosResource() {
        cpds = Utilidades.checkPoolNull(cpds);

        ejercicioMapper = new EjercicioMapper(cpds);
        enunciadoMapper = new EnunciadoMapper(cpds);
        usuarioMapper = new UsuarioMapper(cpds);
    }
    
    //Mas o menos, no es igual a los requisitos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Ejercicios getEjercicios() {
        return new Ejercicios(this.ejercicioMapper.findAll());
    }
    
    /**
     * Para probar por ej (app de chrome): https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo
     * Poner en POST en Payload Raw {"idUsuario": "3"} sin las Comillas y lo de Set "Content-Type" a json
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorYID newEjercicio(Ejercicio ejercicio, @HeaderParam("token") String token, @HeaderParam("idUsuario") String idUsuarioServicio, @HeaderParam("network") String network){//IDUsuario nombre){
      ComprobadorAutenticidad.getUsuarioAdmin(idUsuarioServicio, token, usuarioMapper, network); //Si no es admin ya lanza una expcepcion
        int posibleError = -1;
        //if (Comprobadores.UsuarioEsAdmin(nombre.getIdUsuario()))
            posibleError = ejercicioMapper.insert(ejercicio);
        return new ErrorYID(posibleError);
    }
    
    
    ////////////////////////////////idEjercicio/////////////////////////////////
    
     
    @GET
    @Path("{idEjercicio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ejercicio getEjercicio(@PathParam("idEjercicio") int idEjercicio) {        
        Ejercicio ejercicio = ejercicioMapper.findById(idEjercicio);
        if (ejercicio != null){
            ejercicio.setEnunciados(this.enunciadoMapper.getEnunciadosDeUnEjercicio(ejercicio.getId()));
            return ejercicio;
        }
        else
            throw new WebApplicationException(404);
    }
    
    //En principio no hay put
    /**
    @PUT
    @Path("{idEjercicio}")
    @Consumes("text/json")
    public String putEjercicio(@PathParam("json") String content) {
        return "{\"error\" : \"no\"}";
    }
    **/
    /**
     * Para probar por ej (app de chrome): https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo
     * Poner en DELETE en Payload Raw {"idUsuario": "3"} sin las Comillas y lo de Set "Content-Type" a json
     */
    @DELETE
    @Path("{idEjercicio}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorSimple deleteEjercicio(@PathParam("idEjercicio") int idEjercicio, @HeaderParam("token") String token, @HeaderParam("idUsuario") String idUsuarioServicio, @HeaderParam("network") String network){//, IDUsuario nombre){
        ComprobadorAutenticidad.getUsuarioAdmin(idUsuarioServicio, token, usuarioMapper, network); //Si no es admin ya lanza una expcepcion
        String posibleError = "si";
        Ejercicio aBorrar = ejercicioMapper.findById(idEjercicio);       
        if(this.ejercicioMapper.delete(aBorrar))
            posibleError = "no";     
        return new ErrorSimple(posibleError);
    }
}
