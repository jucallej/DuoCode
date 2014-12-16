/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import utilidades.DatosFijos;

/**
 *
 * @author Johana
 */
@XmlRootElement
public class Tema {
    @XmlElement(name="id")
    private int id;
    
    @XmlElement(name="titulo")
    private String titulo;
    
    @XmlElement(name="descripcion")
    private String descripcion;
    
    @XmlElement(name="orden")
    private int orden;

    @XmlList
    private List<Ruta> lecciones;

     public Tema() {
    }
     
    public Tema(int id, int orden, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.orden = orden;
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
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setLecciones(List<Leccion> lecciones){
        this.lecciones = new ArrayList<>();
        for (Leccion leccion: lecciones){
            this.lecciones.add(new Ruta(DatosFijos.RUTA_LECCIONES+leccion.getId()));
        }
    }
}
