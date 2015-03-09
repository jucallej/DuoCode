/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import puntuador.*;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Date;
import javax.sql.DataSource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mappers.EnvioMapper;
import modelo.Envio;
import modelo.Envios;
import modelo.ErrorYPuntuacion;
import utilidades.DatosFijos;
import utilidades.Utilidades;

/**
 * REST Web Service
 *
 * @author jcarlos
 */
@Path("envios")
public class EnviosResource {

    @Context
    private UriInfo context;
    private EnvioMapper envioMapper;
    
    static private ComboPooledDataSource cpds;

    /**
     * Creates a new instance of EnviosResource
     */
    public EnviosResource() {
        cpds = Utilidades.checkPoolNull(cpds);
        
        envioMapper = new EnvioMapper(cpds);
    }

    
     
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Envios getEnvios() {
        return new Envios(this.envioMapper.findAll());
    }
    
    /*
    La primer vez funciona, despues no te deja  y deberia
    {"codigo":"codigo en java","idUsuario":1,"idEjercicio":3,"lenguajeOrigen":"Java","lenguajeDestino":"C++"}
    */
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorYPuntuacion putEnvio(Envio envio) {
        envio.setId(0);
        envio.setFecha(new Date());
        Puntuador.puntuar(envio);
        if (this.envioMapper.insert(envio) == -1)
            envio.setPuntuacion(-1);
        
        //Habría que comprobar si ha completado la leccion
        
        return new ErrorYPuntuacion(envio.getPuntuacion());
    }
}
