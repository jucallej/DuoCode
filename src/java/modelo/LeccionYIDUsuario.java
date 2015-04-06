/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julián
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LeccionYIDUsuario {
    @XmlElement(name="idUsuarioCompletaLeccion")
    private int idUsuario;
    
    @XmlElement(name="lenguajeCompletadoLeccion")
    private String lenguajeCompletado;
    
    
    @XmlElement(name="leccion")
    private Leccion Leccion;

    public LeccionYIDUsuario() {
    }

    public LeccionYIDUsuario(int idUsuario, String lenguajeCompletado, Leccion Leccion) {
        this.idUsuario = idUsuario;
        this.lenguajeCompletado = lenguajeCompletado;
        this.Leccion = Leccion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public Leccion getLeccion() {
        return Leccion;
    }

    public String getLenguajeCompletado() {
        return lenguajeCompletado;
    } 
}
