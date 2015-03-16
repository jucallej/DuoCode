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
public class Favorito {
    @XmlElement(name="idUsuario")
    private int idUsuario;
    
    @XmlElement(name="idEjercicio")
    private int ejercicio;
    
    @XmlElement(name="lenguajeOrigen")
    private String lenguajeOrigen;
    
    @XmlElement(name="lenguajeDestino")
    private String lenguajeDestino;

     public Favorito() {
    }

    public Favorito(int idUsuario, int ejercicio, String lenguajeOrigen, String lenguajeDestino) {
        this.idUsuario = idUsuario;
        this.ejercicio = ejercicio;
        this.lenguajeOrigen = lenguajeOrigen;
        this.lenguajeDestino = lenguajeDestino;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(int ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getLenguajeOrigen() {
        return lenguajeOrigen;
    }

    public void setLenguajeOrigen(String lenguajeOrigen) {
        this.lenguajeOrigen = lenguajeOrigen;
    }

    public String getLenguajeDestino() {
        return lenguajeDestino;
    }

    public void setLenguajeDestino(String lenguajeDestino) {
        this.lenguajeDestino = lenguajeDestino;
    }
}
