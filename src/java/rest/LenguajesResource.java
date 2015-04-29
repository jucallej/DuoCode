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
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mappers.LenguajeMapper;
import mappers.UsuarioMapper;
import modelo.ErrorYNombreConfirmacion;
import modelo.Lenguaje;
import modelo.Lenguajes;
import utilidades.DatosFijos;
import utilidades.Utilidades;

/**
 * REST Web Service
 *
 * @author jcarlos
 */
@Path("lenguajes")
public class LenguajesResource {

    @Context
    private UriInfo context;
    private LenguajeMapper lenguajeMapper;
    private UsuarioMapper usuarioMapper;
    
    static private ComboPooledDataSource cpds;

    /**
     * Creates a new instance of LenguajesResource
     */
    public LenguajesResource() {
        cpds = Utilidades.checkPoolNull(cpds);
        usuarioMapper = new UsuarioMapper(cpds);

        lenguajeMapper = new LenguajeMapper(cpds);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Lenguajes getLenguajes() {
        List<String> lenguajes = lenguajeMapper.findAll();
        return new Lenguajes(lenguajes);
    }
    
    /**
     * Para probar por ej (app de chrome): https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo
     * Poner en POST en Payload Raw "Java" sin las Comillas y lo de Set "Content-Type" a json
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorYNombreConfirmacion newLenguaje(Lenguaje lenguaje, @HeaderParam("token") String token, @HeaderParam("idUsuario") String idUsuarioServicio, @HeaderParam("network") String network){
        ComprobadorAutenticidad.getUsuarioAdmin(idUsuarioServicio, token, usuarioMapper, network); //Si no es admin ya lanza una expcepcion
        int posibleError = lenguajeMapper.insert(lenguaje.getNombre());
        return new ErrorYNombreConfirmacion(posibleError, lenguaje.getNombre());
    }
}
