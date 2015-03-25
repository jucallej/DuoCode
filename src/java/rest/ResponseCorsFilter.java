/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;


/**
 *
 * @author Julián
 */
public class ResponseCorsFilter implements ContainerResponseFilter{

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext){
        //Si no lo pones, no te deja hacer peticones desde otro sitio que no este ene el mismo dominio (si habres el index desde file y no localhost falla)
        //http://www.codingpedia.org/ama/how-to-add-cors-support-on-the-server-side-in-java-with-jersey/
        
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        //headers.add("Access-Control-Allow-Origin", "http://podcastpedia.org"); //allows CORS requests only coming from podcastpedia.org		
        headers.add("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE");			
	headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia, userID, idUsuario");
        
        
        
        
        //Arriba hay que añadir el nombre de las cabeceras personalizadas que queramos meter (ej; userID)
    }
    
}
