/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import utilidades.DatosFijos;

/**
 *
 * @author Johana
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Ejercicio {
    @XmlElement(name="id")
    private int id;
    
    @XmlElement(name="nombre")
    private String nombre;
    
    @XmlElement(name="enunciados")
    private List<String> enunciados;
    
     public Ejercicio() {
    }
     
    public Ejercicio(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int setId(int idCandidato) {
        return this.id = idCandidato;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
  
    public void setEnunciados(List<Enunciado> enunciados){
        this.enunciados = new ArrayList<>();
        for (Enunciado enunciado: enunciados){
            this.enunciados.add(DatosFijos.RUTA_ENUNCIADOS+enunciado.getIdEnunciado());
        }
    }
}
