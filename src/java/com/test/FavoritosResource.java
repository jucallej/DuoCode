/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import mappers.FavoritoMapper;
import modelo.ErrorSimple;
import modelo.ErrorYID;
import modelo.Favorito;
import modelo.Favoritos;
import utilidades.Comprobadores;
import utilidades.DatosFijos;

/**
 *
 * @author Johana
 */
@Path("favoritos")
public class FavoritosResource {
    
    @Context
    private UriInfo context;
    private FavoritoMapper favoritoMapper;
    public FavoritosResource(){
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
        
        favoritoMapper = new FavoritoMapper(dt);

    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Favoritos getFavoritos() {
        return new Favoritos(this.favoritoMapper.findAll());
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorYID newFavorito(Favorito favorito){
        int posibleError = favoritoMapper.insert(favorito);
        return new ErrorYID(posibleError);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorSimple deleteFavorito(Favorito favorito){
        boolean error = favoritoMapper.delete(favorito);
        String posibleError = (error==false)? "si":"no";
        return new ErrorSimple(posibleError);
    }
}
