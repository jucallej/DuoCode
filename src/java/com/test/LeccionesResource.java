/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

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
import javax.ws.rs.core.MediaType;
import mappers.LeccionConstaEjerciciosMapper;
import mappers.LeccionesMapper;
import mappers.RequisitosLeccionesMapper;
import modelo.ErrorYID;
import modelo.IDsLeccionYEjercidio;
import modelo.IDsLeccionYLeccionDesbloqueadora;
import modelo.Leccion;
import modelo.Lecciones;
import modelo.ErrorSimple;
import utilidades.DatosFijos;

/**
 * REST Web Service
 *
 * @author jcarlos
 */
@Path("lecciones")
public class LeccionesResource {

    @Context
    private UriInfo context;
    private LeccionesMapper leccioneMapper;
    private LeccionConstaEjerciciosMapper leccionConstaEjerciciosMapper;
    private RequisitosLeccionesMapper requisitosLeccionesMapper;

    /**
     * Creates a new instance of Lecciones
     */
    public LeccionesResource() {
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
        
        leccioneMapper = new LeccionesMapper(dt);
        leccionConstaEjerciciosMapper = new LeccionConstaEjerciciosMapper(dt);
        requisitosLeccionesMapper = new RequisitosLeccionesMapper (dt);
    }

    /*
    { "lecciones": ["localhost/duocode/rest/lecciones/idLeccion1", 
    "localhost/duocode/rest/lecciones/idLeccion2"] }
    */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Lecciones getLecciones() {
        return new Lecciones(this.leccioneMapper.findAll());
    }

/*
    {"titulo": "tituloTema", "descripcion": "descripcionTema", 
    "idUsuario": "idDelUsuario", 
    "idEjercicios": ["idEjercicio1", "idEjercicio2"]} ->
    */
    /**
     * Lección sin ejercicios ni requisitos:
{"titulo":"Leccion PruebaPost",
"descripcion":"desc inicial",
"orden":8,
"idTema":1,
"ejercicios":[],
"idPrerrequisitos":[]}
* 
* 
* Leccion con ej y requisitos (no es exactamente igual a como está en los requisitos):
{"titulo":"Leccion PruebaPst","descripcion":"desc inicial","orden":10,"idTema":1,
"ejercicios":["3", "5"],
"leccionesDesbloqueadoras":["1", "6"]}
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorYID newLeccion(Leccion leccion) {
        leccion.setId(0);
        int nuevoId = this.leccioneMapper.insert(leccion);
        leccion.setId(nuevoId);
        List<String> ejercicios = leccion.getEjercicios();
        List<String> leccionesDesbloqueadoras = leccion.getLeccionesDesbloqueadoras();
        
        if(ejercicios != null){ // Lo insertamos
            if (ejercicios.size() > 0)
                for (String intEjercicio : ejercicios)
                    this.leccionConstaEjerciciosMapper.insert(new IDsLeccionYEjercidio(leccion.getId(), Integer.parseInt(intEjercicio)));
        }
        if (leccionesDesbloqueadoras != null){ // Lo insertamos
            if (leccionesDesbloqueadoras.size() > 0)
                for (String intLecciones : leccionesDesbloqueadoras)
                    this.requisitosLeccionesMapper.insert(new IDsLeccionYLeccionDesbloqueadora(leccion.getId(), Integer.parseInt(intLecciones)));
        }
        return new ErrorYID(nuevoId);
    }

    
    //////////////////////////////idLeccion/////////////////////////////////////
    
    /*
    { "titulo": "titulo de la leccion (ej. bucles facil)", 
    "descripcion" : "descripción de la leccion (ej. primeros bucles para practicar)", 
    "fechaCreacion": "17/11/2014", 
    "ejercicios": ["localhost/duocode/rest/ejercicios/idEjercicio1", 
    "localhost/duocode/ejercicios/idEjercicio2"] }
    */
    
    @GET
    @Path("{idLeccion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Leccion getLeccion(@PathParam("idLeccion") int idLeccion) {
        Leccion leccion = leccioneMapper.findById(idLeccion);
        
        List<IDsLeccionYEjercidio> ejerciciosDeLaLeccion = leccionConstaEjerciciosMapper.getIDsLeccionYEjercidioConIDELeccion(leccion.getId());
        leccion.setEjercicios(ejerciciosDeLaLeccion);
        
        List<IDsLeccionYLeccionDesbloqueadora> requisitos = this.requisitosLeccionesMapper.getIDsLeccionYDesbloqueadorasConIDELeccion(leccion.getId());
        leccion.setLeccionesDesbloqueadoras(requisitos);
        
        return leccion;
    }
    /** Es complejito, pero bueno ... ahí queda y parece funcionar
     * EJ (en principio se le puede putear que sigue funcionando (array vacios funciona)):
{"titulo":"Leccion PruebaPst","descripcion":"desc inicial","orden":10,"idTema":1,
"ejercicios":["3", "5"],
"leccionesDesbloqueadoras":["1", "6"]}

     */
    @PUT
    @Path("{idLeccion}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorYID putLeccion(@PathParam("idLeccion") int id, Leccion leccion) { //Un poco más limpio
        leccion.setId(id);

        Leccion leccionExistente = this.leccioneMapper.findById(id);
        
        if (leccionExistente != null){//Exite, y lo podemos actualizar
            //Sacamos los ej y lecciones existentes
            List<IDsLeccionYEjercidio> ejerciciosDeLaLeccion = leccionConstaEjerciciosMapper.getIDsLeccionYEjercidioConIDELeccion(leccion.getId());
            leccionExistente.setEjercicios(ejerciciosDeLaLeccion);


            List<IDsLeccionYLeccionDesbloqueadora> requisitos = this.requisitosLeccionesMapper.getIDsLeccionYDesbloqueadorasConIDELeccion(leccion.getId());
            leccionExistente.setLeccionesDesbloqueadoras(requisitos);
            
            //Actualizamos la leccion
            this.leccioneMapper.update(leccion);
            //Ahora nos queda mirar los ej y las lecciones que desbloquean
            //Para no borrar algo que queramos mantener (que igual desencadena borrados en cadena (ej ejercicio resuleto)) vamos a hacerlo poco a poco
            
            //Estos tienen en ruta tienen int, es un poco extraño, pero funciona, son los que queremos tener
            List<String> ejercicios = leccion.getEjercicios();
            List<String> leccionesDesbloqueadoras = leccion.getLeccionesDesbloqueadoras();
            
            //Miramos los que ya estan
            if (leccionExistente.getEjercicios() != null){
                leccionExistente.quitarLeccionesQueBorramosYEvitarAnhadir(ejercicios, this.leccionConstaEjerciciosMapper);
            }
            
            if (leccionExistente.getLeccionesDesbloqueadoras() != null){
                leccionExistente.quitarEjerciciosQueBorramosYEvitarAnhadir(leccionesDesbloqueadoras, this.requisitosLeccionesMapper);
            }
            
            //Si llegamos hasta aquí y la leccion que nos han dado sigue teniendo ej entonces esos no estaban antes y los metemos con en el POST
            if(ejercicios != null){ // Lo insertamos
                if (ejercicios.size() > 0)
                    for (String intEjercicio : ejercicios)
                        this.leccionConstaEjerciciosMapper.insert(new IDsLeccionYEjercidio(leccion.getId(), Integer.parseInt(intEjercicio)));
            }
            if (leccionesDesbloqueadoras != null){ // Lo insertamos
                if (leccionesDesbloqueadoras.size() > 0)
                    for (String intLecciones : leccionesDesbloqueadoras)
                        this.requisitosLeccionesMapper.insert(new IDsLeccionYLeccionDesbloqueadora(leccion.getId(), Integer.parseInt(intLecciones)));
            }
        }
        
        return new ErrorYID(id);
    }
    
    @DELETE
    @Path("{idLeccion}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ErrorSimple deleteTema(@PathParam("idLeccion") int id){ //Habría que comprobar la identidad de alguna manera, al igual que en los post y put
        String posibleError = "si";
        Leccion aBorrar = leccioneMapper.findById(id);
            if(this.leccioneMapper.delete(aBorrar))
                posibleError = "no";

        return new ErrorSimple(posibleError);
    }
    
    
}
