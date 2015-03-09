/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.List;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import mappers.LeccionesMapper;
import modelo.Tema;
import mappers.TemaMapper;
import modelo.ErrorSimple;
import modelo.ErrorYID;
import modelo.Temas;
import utilidades.DatosFijos;
import utilidades.Utilidades;

/**
 * REST Web Service
 *
 * @author jcarlos
 */
@Path("temas")
public class TemasResource {

    @Context
    private UriInfo context;
    private TemaMapper temaMapper;
    private LeccionesMapper leccioneMapper;
    
    static private ComboPooledDataSource cpds;

    /**
     * Creates a new instance of DuocodeResource
     */
    public TemasResource() {
        cpds = Utilidades.checkPoolNull(cpds);
        
        temaMapper = new TemaMapper(cpds);
        leccioneMapper = new LeccionesMapper(cpds);
    }

    /**
     * Retrieves representation of an instance of com.test.TemasResource
     * @return an instance of java.lang.String
     * 
     * { "temas": ["localhost/duocode/rest/temas/idTema1", 
     * "localhost/duocode/rest/temas/idTema2"] }
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Temas getTemas() {
        List<Tema> temas = temaMapper.findAll();
        return new Temas(temas);
    }

    
    
/* {"titulo": "tituloTema", 
    "descripcion": "descripcionTema", 
    "idUsuario": "idDelUsuario", 
    "idLecciones": ["idLeccion1", "idLeccion2"]} ->*/


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorYID newTema(Tema tema) {
        tema.setId(0);
        if(tema.getOrden() == 0 || temaMapper.getTemasConOrden(tema.getOrden()).size()>0)//0 es el int cuando no se le pasa en json
            return new ErrorYID(-1);
        int nuevoID = temaMapper.insert(tema);
        return new ErrorYID(nuevoID);
    }

    
    /////////////////////////////IDTEMA/////////////////////////////////////
   /*
    { "titulo": "titulo del tema (ej. bucles)", 
    "descripcion" : "descripción del tema (ej. los bucles hacen bla bla)", 
    "fechaCreacion": "17/11/2014", "lecciones": ["localhost/duocode/rest/lecciones/idLeccion1", 
    "localhost/duocode/rest/lecciones/idLeccion2"] }
    */
    
    @GET
    @Path("{idTema}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tema getTema(@PathParam("idTema") int idTema) throws InterruptedException {
        //TODO return proper representation object
        Tema tema = temaMapper.findById(idTema);
        if (tema != null){
            tema.setLecciones(this.leccioneMapper.getLeccionesDeUnTema(tema.getId()));
            return tema;
        }
        else
            throw new WebApplicationException(404);
    }
    
    
    /*
    {"titulo": "tituloTema", 
    "descripcion": "descripcionTema", 
    "idUsuario": "idDelUsuario", 
    "idLecciones": ["idLeccion1", "idLeccion2"]}
    */
    @PUT
    @Path("{idTema}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorSimple putTema(@PathParam("idTema") int id, Tema tema) {
        Tema aModificar = temaMapper.findById(id);
        String error = "si";
        if (aModificar != null){
            tema.setId(id);
            temaMapper.update(tema);
            error = "no";
        }
        return new ErrorSimple(error);
    }
    
    @DELETE
    @Path("{idTema}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorSimple deleteTema(@PathParam("idTema") int id){
        String posibleError = "si";
        Tema aBorrar = temaMapper.findById(id);
            if(this.temaMapper.delete(aBorrar))
                posibleError = "no";

        return new ErrorSimple(posibleError);
    }
}
