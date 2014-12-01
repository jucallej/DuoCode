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
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mappers.LenguajeMapper;
import modelo.Lenguaje;
import modelo.Lenguajes;

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

    /**
     * Creates a new instance of LenguajesResource
     */
    public LenguajesResource() {
        DataSource dt = null;
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
                cpds.setDriverClass("org.gjt.mm.mysql.Driver");
        } catch (PropertyVetoException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        cpds.setJdbcUrl("jdbc:mysql://localhost/Duocode");
        cpds.setUser("root");
        cpds.setPassword("");
        cpds.setAcquireRetryAttempts(1);
        cpds.setAcquireRetryDelay(1);
        cpds.setBreakAfterAcquireFailure(true);
        dt = cpds;

        lenguajeMapper = new LenguajeMapper(dt);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Lenguajes getLenguajes() {
        List<Lenguaje> lenguajes = lenguajeMapper.findAll();
        
        return new Lenguajes(lenguajes);
    }
    
    @POST
    @Produces("text/html")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String newLenguaje(@FormParam("json") String Json) throws IOException {
        return "error";
    }

}
