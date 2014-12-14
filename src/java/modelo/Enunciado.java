/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julián
 */
@XmlRootElement
public class Enunciado {
    @XmlElement(name="idEnunciado")
    private int enunciado;
    
    @XmlElement(name="codigo")
    private String codigo;
    
    @XmlElement(name="idDelEjercicioQueResuelve")
    private int idEjercicio;
    
    @XmlElement(name="nombreLenguaje")
    private String lenguaje;
    
    @XmlElement(name="fechaCreacion")
    private Date fechaCreacion;

     public Enunciado() {
    }

    public Enunciado(int idEenunciado, String codigo, int idEjercicio, String lenguaje, Date fechaCreacion) {
        this.enunciado = idEenunciado;
        this.codigo = codigo;
        this.idEjercicio = idEjercicio;
        this.lenguaje = lenguaje;
        this.fechaCreacion = fechaCreacion;
    }
    
    public int getIdEnunciado() {
        return enunciado;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setEnunciado(int enunciado) {
        this.enunciado = enunciado;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }  
}
