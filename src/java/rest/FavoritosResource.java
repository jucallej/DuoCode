/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import autentificacion.ComprobadorAutenticidad;
import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import mappers.FavoritoMapper;
import mappers.UsuarioMapper;
import modelo.ErrorSimple;
import modelo.Favorito;
import modelo.Favoritos;
import modelo.Usuario;

import utilidades.DatosFijos;
import utilidades.Utilidades;

/**
 *
 * @author Johana
 */
@Path("favoritos")
public class FavoritosResource {
    
    @Context
    private UriInfo context;
    private FavoritoMapper favoritoMapper;
    private UsuarioMapper usuarioMapper;
    
    static private ComboPooledDataSource cpds;
    
    public FavoritosResource(){
        cpds = Utilidades.checkPoolNull(cpds);
        usuarioMapper = new UsuarioMapper(cpds);
        
        favoritoMapper = new FavoritoMapper(cpds);

    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Favoritos getFavoritos(@HeaderParam("token") String token, @HeaderParam("idUsuario") String idUsuarioServicio, @HeaderParam("network") String network) {
        ComprobadorAutenticidad.getUsuarioAdmin(idUsuarioServicio, token, usuarioMapper, network); //Si no es admin ya lanza una expcepcion
        return new Favoritos(this.favoritoMapper.findAll());
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorSimple newFavorito(Favorito favorito, @HeaderParam("token") String token, @HeaderParam("idUsuario") String idUsuarioServicio, @HeaderParam("network") String network){
        Usuario usuario = ComprobadorAutenticidad.getUsuario(idUsuarioServicio, token, usuarioMapper, network); //Si no es admin ya lanza una expcepcion
        if (usuario.getId() != favorito.getIdUsuario()) throw new WebApplicationException(401);
        int posibleError = favoritoMapper.insert(favorito);
        String error = (posibleError<0)? "si":"no";
        return new ErrorSimple(error); //En realidad no hay id
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //En Header Form = "userID", "1"
    //En Payload(RAW) {"favoritos":[{"idUsuario":1,"idEjercicio":4,"lenguajeOrigen":"C++","lenguajeDestino":"C++"},{"idUsuario":1,"idEjercicio":4,"lenguajeOrigen":"Java","lenguajeDestino":"C++"}]} 
    public ErrorSimple deleteFavorito(Favoritos favorito, @HeaderParam("userID") int usuario, @HeaderParam("token") String token, @HeaderParam("idUsuario") String idUsuarioServicio, @HeaderParam("network") String network){
        Usuario usuarioAut = ComprobadorAutenticidad.getUsuario(idUsuarioServicio, token, usuarioMapper, network); //Si no es admin ya lanza una expcepcion
        for(Favorito fav: favorito.getFavoritos()){
            if (usuarioAut.getId() != fav.getIdUsuario()) throw new WebApplicationException(401);
        }
        
        boolean error = favoritoMapper.updateFavoritos(favorito, usuario);
        String posibleError = (error==true)? "si":"no";
        return new ErrorSimple(posibleError);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)    
     public ErrorSimple deleteFavorito(Favorito fav, @HeaderParam("token") String token, @HeaderParam("idUsuario") String idUsuarioServicio, @HeaderParam("network") String network){
        Usuario usuario = ComprobadorAutenticidad.getUsuario(idUsuarioServicio, token, usuarioMapper, network); //Si no es admin ya lanza una expcepcion
        if (usuario.getId() != fav.getIdUsuario()) throw new WebApplicationException(401);
        String posibleError = "si";
        Favorito aBorrar = favoritoMapper.findById(fav);
        if(this.favoritoMapper.delete(aBorrar))
            posibleError = "no";

        return new ErrorSimple(posibleError);
    }
}
