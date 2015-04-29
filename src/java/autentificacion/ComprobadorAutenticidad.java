/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autentificacion;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
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
public final class ComprobadorAutenticidad {

    private static GoogleResponse comprobarVeracidadUsuarioGoogle(String idUsuarioGoogle, String token){
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
    
    private static FacebookResponse comprobarVeracidadUsuarioFacebook(String idUsuarioFacebook, String token){
        FacebookResponse respuestaParseada = null;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(DatosFijos.URL_COMPROBADOR_FACEBOOK);
        
        Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN_TYPE);
        invocationBuilder.header("Authorization", "Bearer " + token);
        
        Response response = invocationBuilder.get();
        if (response.getStatus() == 200){// El token existe y nos devolverá datos
            //No terminaba de funcionar de la manera normal
            JsonReader rdr = Json.createReader(new ByteArrayInputStream(response.readEntity(String.class).getBytes(StandardCharsets.UTF_8)));
            JsonObject obj = rdr.readObject();
            FacebookResponse respuestaParseadaTemp = new FacebookResponse(obj.getString("id"), obj.getString("name"), obj.getJsonObject("picture").getJsonObject("data").getString("url"));
            if (respuestaParseadaTemp.getId().equals(idUsuarioFacebook)) respuestaParseada = respuestaParseadaTemp; //Es quien dice ser
        }
        
        return respuestaParseada;
    }
    
    
    
    /**
     * Devulve el usuario perteneciente al id de google y token pasados.
     * @param idUsuarioServicio
     * @param token
     * @return null si idUsuarioServicio y el token son mentira, el usuario correspondiente ( o uno nuevo si no existe), si es verdad
     */
    public static Usuario getUsuario(String idUsuarioServicio, String token, UsuarioMapper usuarioMapper, String network){
        Usuario usuario = null;
        RespuestaServiciosLogin respuestaServiciosLogin= null;
        String idUsuarioGoogle = null;
        String idUsuarioFacebook = null;
        
        if (network.equalsIgnoreCase("google")){
            usuario = usuarioMapper.findUsuarioIDGoogle(idUsuarioServicio);
            respuestaServiciosLogin = ComprobadorAutenticidad.comprobarVeracidadUsuarioGoogle(idUsuarioServicio, token);
            idUsuarioGoogle = idUsuarioServicio;
        }
        else if (network.equalsIgnoreCase("facebook")){
            usuario = usuarioMapper.findUsuarioIDFacebook(idUsuarioServicio);
            respuestaServiciosLogin = ComprobadorAutenticidad.comprobarVeracidadUsuarioFacebook(idUsuarioServicio, token);
            idUsuarioFacebook = idUsuarioServicio;
        }
        if (respuestaServiciosLogin == null) usuario = null; // Aunque exista, el usuario miente
        else{
            if (usuario == null){//El usuario no existe, pero dice la verdad. Lo creamos
                usuario = new Usuario(0, idUsuarioGoogle, idUsuarioFacebook, (short)0);
                int nuevoId = usuarioMapper.insert(usuario) ;
                usuario.setId(nuevoId);
            }
            
            usuario.setName(respuestaServiciosLogin.getName());
            usuario.setPicture(respuestaServiciosLogin.getPictureURL());
            
        }
        
        if (usuario == null) throw new WebApplicationException(401);
        
        return usuario;
    }
    
    public static Usuario getUsuarioAdmin(String idUsuarioServicio, String token, UsuarioMapper usuarioMapper, String network){
        Usuario usuario = getUsuario(idUsuarioServicio, token, usuarioMapper, network);
        
        if (usuario.getRol() <= 0) throw new WebApplicationException(401);
        
        return usuario;
    }
    
}
