/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import mappers.FavoritoMapper;
import modelo.ErrorSimple;
import modelo.Favorito;
import modelo.Favoritos;

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
    public ErrorSimple newFavorito(Favorito favorito){
        int posibleError = favoritoMapper.insert(favorito);
        String error = (posibleError<0)? "si":"no";
        return new ErrorSimple(error); //En realidad no hay id
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //En Header Form = "userID", "1"
    //En Payload(RAW) {"favoritos":[{"idUsuario":1,"idEjercicio":4,"lenguajeOrigen":"C++","lenguajeDestino":"C++"},{"idUsuario":1,"idEjercicio":4,"lenguajeOrigen":"Java","lenguajeDestino":"C++"}]} 
    public ErrorSimple deleteFavorito(Favoritos favorito, @HeaderParam("userID") int usuario){
        boolean error = favoritoMapper.updateFavoritos(favorito, usuario);
        String posibleError = (error==true)? "si":"no";
        return new ErrorSimple(posibleError);
    }
}
