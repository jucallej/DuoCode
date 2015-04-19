/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package google;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mappers.UsuarioMapper;
import modelo.Usuario;
import utilidades.DatosFijos;

/**
 *
 * @author Julián
 */
public final class ComprobadorGoogle {

    private static GoogleResponse comprobarVeracidadUsuario(String idUsuarioGoogle, String token){
        GoogleResponse respuestaParseada = null;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(DatosFijos.URL_COMPROBADOR_GOOGLE);
        
        Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN_TYPE);
        invocationBuilder.header("Authorization", "Bearer " + token);
        
        Response response = invocationBuilder.get();
        if (response.getStatus() == 200){// El token existe y nos devolverá datos
            GoogleResponse respuestaParseadaTemp = response.readEntity(GoogleResponse.class);
            if (respuestaParseadaTemp.getId().equals(idUsuarioGoogle)) respuestaParseada = respuestaParseadaTemp; //Es quien dice ser
        }
        
        return respuestaParseada;
    }
    
    /**
     * Devulve el usuario perteneciente al id de google y token pasados.
     * @param idUsuarioGoogle
     * @param token
     * @return null si idUsuarioGoogle y el token son mentira, el usuario correspondiente ( o uno nuevo si no existe), si es verdad
     */
    public static Usuario getUsuario(String idUsuarioGoogle, String token, UsuarioMapper usuarioMapper){
        Usuario usuario = usuarioMapper.findUsuarioIDGoogle(idUsuarioGoogle);
        
        GoogleResponse googleResponse = ComprobadorGoogle.comprobarVeracidadUsuario(idUsuarioGoogle, token);
        
        if (googleResponse == null) usuario = null; // Aunque exista, el usuario miente
        else{
            if (usuario == null){//El usuario no existe, pero dice la verdad. Lo creamos
                usuario = new Usuario(0, idUsuarioGoogle, (short)0);
                int nuevoId = usuarioMapper.insert(usuario) ;
                usuario.setId(nuevoId);
            }
            
            usuario.setName(googleResponse.getName());
            usuario.setPicture(googleResponse.getPicture());
            
        }
        
        if (usuario == null) throw new WebApplicationException(401);
        
        return usuario;
    }
    
    public static Usuario getUsuarioAdmin(String idUsuarioGoogle, String token, UsuarioMapper usuarioMapper){
        Usuario usuario = getUsuario(idUsuarioGoogle, token, usuarioMapper);
        
        if (usuario.getRol() <= 0) throw new WebApplicationException(401);
        
        return usuario;
    }
    
}
