/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


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
    private Ejercicios ejercicios;
    
    @XmlElement(name="idPrerrequisitos")
    private Lecciones leccionesDesbloqueadoras;

    public Leccion() {
    }

    public Leccion(int id, String titulo, String descripcion, int orden, int idTema) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.orden = orden;
        this.idTema = idTema;
    }

    public Leccion(int id, String titulo, String descripcion, int orden, int idTema, Ejercicios ejercicios, Lecciones leccionesDesbloqueadoras) {
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

    public void setEjercicios(Ejercicios ejercicios) {
        this.ejercicios = ejercicios;
    }

    public void setLeccionesDesbloqueadoras(Lecciones leccionesDesbloqueadoras) {
        this.leccionesDesbloqueadoras = leccionesDesbloqueadoras;
    }

    public Ejercicios getEjercicios() {
        return ejercicios;
    }

    public Lecciones getLeccionesDesbloqueadoras() {
        return leccionesDesbloqueadoras;
    } 
}
