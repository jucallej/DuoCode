/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Date;
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
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mappers.CandidatoMapper;
import mappers.EnvioMapper;
import mappers.UsuarioMapper;
import mappers.UsuarioVotaCandidatoMapper;
import modelo.Candidato;
import modelo.Candidatos;
import modelo.ErrorSimple;
import modelo.ErrorYID;
import modelo.IDUsuario;
import modelo.Usuario;
import modelo.UsuarioVotaCandidato;
import modelo.VotoIDUsuario;
import utilidades.DatosFijos;

/**
 * REST Web Service
 *
 * @author jcarlos
 */
@Path("candidatos")
public class CandidatosResource {
/////////////////////////////////////
    @Context
    private UriInfo context;
    private CandidatoMapper candidatoMapper;
    private UsuarioMapper usuarioMapper;
    private UsuarioVotaCandidatoMapper usuarioVotaCandidatoMapper;

    /**
     * Creates a new instance of CandidatosResource
     */
    public CandidatosResource() {
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
        
        candidatoMapper = new CandidatoMapper(dt);
        usuarioMapper = new UsuarioMapper(dt);
        usuarioVotaCandidatoMapper = new UsuarioVotaCandidatoMapper(dt);
    }

    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Candidatos getCandidatos() {
        return new Candidatos(this.candidatoMapper.findAll());
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorYID newCandidato(Candidato candidato){
        candidato.setId(0);
        candidato.setFecha(new Date());
        int nuevoID = candidatoMapper.insert(candidato);
        return new ErrorYID(nuevoID);
    }
    
    
    ////////////////////////////////idCandidato/////////////////////////////////
    
     
    @GET
    @Path("{idCandidato}")
    @Produces(MediaType.APPLICATION_JSON)
    public Candidato getCandidato(@PathParam("idCandidato") int id) {
        //TODO return proper representation object
        Candidato candidato = candidatoMapper.findById(id);
        //Aquí habría que mirar la tabla usuariovotacandidato y ver los votos que tiene -> candidato.setvotos(votosMapper.algo());
        return candidato;
    }
    
    @PUT
    @Path("{idCandidato}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    /*Poner en raw por ej
    {"idUsuario": 1, "voto":"pos"}
    */
    public ErrorSimple putCandidato(@PathParam("idCandidato") int idCandidato, VotoIDUsuario votoIDUsuario) {
        //No hace falta decirle al usuario que ha votado no? eso ya lo sabrá el. Y el usuario podría votar positivo si ya había votado positvo para quitar el voto. 
        //Es decir hace falta pedir si el voto es "pos" o "neg" tambien.
        Candidato aModificar = candidatoMapper.findById(idCandidato);
        Usuario usuario = usuarioMapper.findById(votoIDUsuario.getIdUsuario());
        String error = "si";

        if(aModificar != null && usuario != null){ //Por aquí creo que hay que modificar algo, según el usuario voto pos o neg
            UsuarioVotaCandidato uvc = new UsuarioVotaCandidato(votoIDUsuario.getIdUsuario(), idCandidato);
            error = "no";
            if(usuarioVotaCandidatoMapper.insert(uvc) == -1){
                usuarioVotaCandidatoMapper.delete(uvc);
            }
        }
        
        return new ErrorSimple(error);
    }
    
    
    @DELETE
    @Path("{idCandidato}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorSimple deleteCandidato(@PathParam("idCandidato") int id){
        String posibleError = "si";
        Candidato aBorrar = candidatoMapper.findById(id);
            if(this.candidatoMapper.delete(aBorrar))
                posibleError = "no";

        return new ErrorSimple(posibleError);
    }
}
