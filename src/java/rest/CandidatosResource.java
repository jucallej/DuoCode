/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Date;
import javax.sql.DataSource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mappers.CandidatoMapper;
import mappers.CandidatoMapperSinGestionadoPor;
import mappers.UsuarioMapper;
import mappers.UsuarioVotaCandidatoMapper;
import modelo.Candidato;
import modelo.Candidatos;
import modelo.ErrorSimple;
import modelo.ErrorYID;
import modelo.IDUsuario;
import modelo.IDUsuarioYVoto;
import modelo.IDUsuarioYVotoYCandidato;
import modelo.Usuario;
import modelo.UsuarioVotaCandidato;
import modelo.VotoIDUsuario;
import utilidades.DatosFijos;
import utilidades.Utilidades;

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
    private CandidatoMapperSinGestionadoPor candidatoMapperSinGestionadoPor;
    
    static private ComboPooledDataSource cpds;

    /**
     * Creates a new instance of CandidatosResource
     */
    public CandidatosResource() {
        cpds = Utilidades.checkPoolNull(cpds);
        
        candidatoMapper = new CandidatoMapper(cpds);
        usuarioMapper = new UsuarioMapper(cpds);
        usuarioVotaCandidatoMapper = new UsuarioVotaCandidatoMapper(cpds);
        candidatoMapperSinGestionadoPor = new CandidatoMapperSinGestionadoPor(cpds);
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
        candidato.setEstado(0);
        int nuevoID;
        if(candidato.getGestionadoPor() <= 0){
            nuevoID = candidatoMapperSinGestionadoPor.insert(candidato);
        }
        else nuevoID = candidatoMapper.insert(candidato);
        return new ErrorYID(nuevoID);
    }
    
    
    ////////////////////////////////idCandidato/////////////////////////////////
    
     
    @GET
    @Path("{idCandidato}")
    @Produces(MediaType.APPLICATION_JSON)
    public Candidato getCandidato(@PathParam("idCandidato") int id) {
        Candidato candidato = candidatoMapper.findById(id);
        candidato.setVotosPos(usuarioVotaCandidatoMapper.getVotosPosDeUnCandidato(id));
        candidato.setVotosNeg(usuarioVotaCandidatoMapper.getVotosNegDeUnCandidato(id));
        return candidato;
    }
    
    @PUT
    @Path("{idCandidato}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Poner en raw el id del usuario: {"idUsuario":1}
    //poner en la url /candidatos/3/1  (3 es el idCandidato y 1 es voto positivo, 0 es negativo)
    public ErrorSimple putCandidato(@PathParam("idCandidato") int idCandidato, IDUsuarioYVotoYCandidato iDUsuarioYVotoYCandidato) {
        IDUsuarioYVoto idUsuario = iDUsuarioYVotoYCandidato.getiDUsuarioYVoto(); 
        Candidato candidato = iDUsuarioYVotoYCandidato.getCandidato();
        String error = "si";
        
        if (idUsuario != null){
        int posNeg = idUsuario.getVoto();
            Candidato aModificar = candidatoMapper.findById(idCandidato);
            Usuario usuario = usuarioMapper.findById(idUsuario.getIdUsuario());
                

                if(aModificar != null && usuario != null){
                    UsuarioVotaCandidato nuevo = new UsuarioVotaCandidato(idUsuario.getIdUsuario(), idCandidato, posNeg);
                    error = "no";
                    //Si no existe lo creamos, si existe lo borramos
                    UsuarioVotaCandidato antiguo = usuarioVotaCandidatoMapper.findById(nuevo); //Si el nuevo ya existe se guarda en antiguo
                    if (antiguo == null){
                        usuarioVotaCandidatoMapper.insert(nuevo);
                    }
                    else {
                        if(antiguo.getVoto() == nuevo.getVoto())
                            usuarioVotaCandidatoMapper.delete(antiguo);
                        else usuarioVotaCandidatoMapper.update(nuevo);
                    }
                }

        }
        
        if (candidato != null){
            Candidato aModificar = candidatoMapper.findById(idCandidato);
            if (aModificar != null){
                candidato.setId(idCandidato);
                candidato.setFecha(aModificar.getFecha());
                candidatoMapper.update(candidato);
                error = "no";
            }
        }
        
        return new ErrorSimple(error);
    } 
    
    /**
    //Para que un usuario administrador (rol != 0) pueda gestionar un candidato
    //se usa el Put del candidato con el id del usuario administrador en raw.
    @PUT
    @Path("{idCandidato}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Poner en raw el id del usuario: {"idUsuario":1}
   
    public ErrorSimple putCandidato(@PathParam("idCandidato") int idCandidato, IDUsuario idUsuario) {
        Candidato aModificar = candidatoMapper.findById(idCandidato);
        Usuario usuario = usuarioMapper.findById(idUsuario.getIdUsuario());
       
        String error = "si";
        if(usuario.getId()!=0){
        
            error = "no";
            aModificar.setGestionadoPor(usuario.getId());

            candidatoMapper.update(aModificar);
        }
        return new ErrorSimple(error);
    }
    **/
    
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
