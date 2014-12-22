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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import mappers.EjercicioMapper;
import mappers.EnunciadoMapper;
import modelo.Ejercicios;
import modelo.Enunciados;
import modelo.ErrorSimple;
import modelo.ErrorYID;
import modelo.IDUsuario;
import utilidades.Comprobadores;
import utilidades.DatosFijos;

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

    /**
     * Creates a new instance of EjerciciosResource
     */
    public EjerciciosResource() {
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

        ejercicioMapper = new EjercicioMapper(dt);
        enunciadoMapper = new EnunciadoMapper(dt);
    }
    
    //Mas o menos, no es igual a los requisitos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Ejercicios getEjercicios() {
        List<Integer> ejercicios = ejercicioMapper.findAll();
        return new Ejercicios(ejercicios);
    }
    
    /**
     * Para probar por ej (app de chrome): https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo
     * Poner en POST en Payload Raw {"idUsuario": "3"} sin las Comillas y lo de Set "Content-Type" a json
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorYID newEjercicio(IDUsuario nombre){
        int posibleError = -1;
        if (Comprobadores.UsuarioEsAdmin(nombre.getIdUsuario()))
            posibleError = ejercicioMapper.insert(0);
        return new ErrorYID(posibleError);
    }
    
    
    ////////////////////////////////idEjercicio/////////////////////////////////
    
     
    @GET
    @Path("{idEjercicio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Enunciados getEjercicio(@PathParam("idEjercicio") int idEjercicio) {
        return new Enunciados(enunciadoMapper.getEnunciadosDeUnEjercicio(idEjercicio));
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
    public ErrorSimple deleteEjercicio(@PathParam("idEjercicio") int idEjercicio){//, IDUsuario nombre){
        String posibleError = "si";

        //if (Comprobadores.UsuarioEsAdmin(nombre.getIdUsuario())){
            if(this.ejercicioMapper.delete(idEjercicio))
                posibleError = "no";

        System.out.println(posibleError);
        return new ErrorSimple(posibleError);
    }
}
