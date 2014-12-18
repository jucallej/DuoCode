/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import mappers.LeccionConstaEjerciciosMapper;
import mappers.RequisitosLeccionesMapper;
import utilidades.DatosFijos;


@XmlRootElement
public class Leccion {    
    @XmlElement(name="id")
    private int id;
    
    @XmlElement(name="titulo")
    private String titulo;
    
    @XmlElement(name="descripcion")
    private String descripcion;
    
    @XmlElement(name="orden")
    private int orden;
    
    @XmlElement(name="idTema")
    private int idTema;

    @XmlElement(name="ejercicios")
    private List<String> ejercicios;

    @XmlElement(name="leccionesDesbloqueadoras")
    private List<String> leccionesDesbloqueadoras;
    
    private List<Integer> intEjercicios;
    private List<Integer> intLecionesDesbloqueadoras;

    public Leccion() {
    }

    public Leccion(int id, String titulo, String descripcion, int orden, int idTema) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.orden = orden;
        this.idTema = idTema;
    }

    public Leccion(int id, String titulo, String descripcion, int orden, int idTema, List<String> ejercicios, List<String> leccionesDesbloqueadoras) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.orden = orden;
        this.idTema = idTema;
        this.ejercicios = ejercicios;
        this.leccionesDesbloqueadoras = leccionesDesbloqueadoras;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public int getIdTema() {
        return idTema;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public void setEjercicios(List<IDsLeccionYEjercidio> ejercicios) {
        this.ejercicios = new ArrayList<>();
        intEjercicios = new ArrayList<>();
        for (IDsLeccionYEjercidio ejercicio: ejercicios){
            this.ejercicios.add(DatosFijos.RUTA_EJERCICIOS+ejercicio.getIdEjercicio());
            this.intEjercicios.add(ejercicio.getIdEjercicio());
        }
    }

    public void setLeccionesDesbloqueadoras(List<IDsLeccionYLeccionDesbloqueadora> leccionesDesbloqueadoras) {
        this.leccionesDesbloqueadoras = new ArrayList<>();
        this.intLecionesDesbloqueadoras = new ArrayList<>();
        for (IDsLeccionYLeccionDesbloqueadora requisito: leccionesDesbloqueadoras){
            this.leccionesDesbloqueadoras.add(DatosFijos.RUTA_LECCIONES+requisito.getIdLeccionDesbloqueadora());
            this.intLecionesDesbloqueadoras.add(requisito.getIdLeccionDesbloqueadora());
        }
    }

    public List<String> getEjercicios() {
        return ejercicios;
    }

    public List<String> getLeccionesDesbloqueadoras() {
        return leccionesDesbloqueadoras;
    } 
    
    //Si ya existe lo borra de lecciones (no hace falta añadirlo, y sino lo mantiende y devuelve false)
    private boolean contienenLecciones(List<String> leccionesDesbloqueadoras, Integer leccionesDesbloqueadorasExistente) {
        boolean encontrado = false;
        Iterator<String> iterador = leccionesDesbloqueadoras.iterator();
        
        while (!encontrado && iterador.hasNext()){
            String dato = iterador.next();
            if (Integer.parseInt(dato) == leccionesDesbloqueadorasExistente){
                encontrado = true;
                iterador.remove();
            }
        }
        
        return encontrado;
    }
    
    //Si ya existe lo borra de ejercicios (no hace falta añadirlo, y sino lo mantiende y devuelve false)
    private boolean contienenEjercicios(List<String> ejercicios, Integer ejerciciosExistente) {
        boolean encontrado = false;
        Iterator<String> iterador = ejercicios.iterator();
        
        while (!encontrado && iterador.hasNext()){
            String dato = iterador.next();
            
            if (Integer.parseInt(dato) == ejerciciosExistente){
                encontrado = true;
                iterador.remove();
            }
        }
        
        return encontrado;
    }

    public List<Integer> getIntEjercicios() {
        return intEjercicios;
    }

    public List<Integer> getIntLecionesDesbloqueadoras() {
        return intLecionesDesbloqueadoras;
    }

    public void quitarLeccionesQueBorramosYEvitarAnhadir(List<String> ejercicios, LeccionConstaEjerciciosMapper leccionConstaEjerciciosMapper) {
        //Miramos en los que ya están, si no los queremos mantener los borramos, y si ya están los quitamos de las variables de arriba "ejercicios" y "leccionesDesbloqueadoras"
        for (Integer ejerciciosExistente : intEjercicios){
            //if (!ejercicios.contienenEjercicios(ejerciciosExistente)) //Si ya existe lo borra de ejercicios (no hace falta añadirlo, y sino lo mantiende y devuelve false)
            if (!contienenEjercicios(ejercicios, ejerciciosExistente)) 
                leccionConstaEjerciciosMapper.delete(new IDsLeccionYEjercidio(this.getId(), ejerciciosExistente));
        }
    }

    public void quitarEjerciciosQueBorramosYEvitarAnhadir(List<String> leccionesDesbloqueadoras, RequisitosLeccionesMapper requisitosLeccionesMapper) {
        //Miramos en los que ya están, si no están incluidos los borramos
        for (Integer leccionesDesbloqueadorasExistente : intLecionesDesbloqueadoras){
            if (!contienenLecciones(leccionesDesbloqueadoras, leccionesDesbloqueadorasExistente)) //Similar al if de arriba
                requisitosLeccionesMapper.delete(new IDsLeccionYLeccionDesbloqueadora(this.getId(), leccionesDesbloqueadorasExistente));
        }
    }
}
