/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Envio {    
    @XmlElement(name="id")
    private int id;
    
    @XmlElement(name="codigo")
    private String codigo;
    
    @XmlElement(name="fecha")
    private Date fecha;
    
    @XmlElement(name="puntuacion")
    private int puntuacion;
    
    @XmlElement(name="idUsuario")
    private int idUsuario;
    
    @XmlElement(name="idEjercicio")
    private int idEjercicio;
    
    @XmlElement(name="lenguajeOrigen")
    private String lenguajeOrigen;
    
    @XmlElement(name="lenguajeDestino")
    private String lenguajeDestino;

    public Envio() {
    }

    public Envio(int id, String codigo, Date fecha, int puntuacion, int idUsuario, int idEjercicio, String lenguajeOrigen, String lenguajeDestino) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.puntuacion = puntuacion;
        this.idUsuario = idUsuario;
        this.idEjercicio = idEjercicio;
        this.lenguajeOrigen = lenguajeOrigen;
        this.lenguajeDestino = lenguajeDestino;
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public String getLenguajeOrigen() {
        return lenguajeOrigen;
    }

    public String getLenguajeDestino() {
        return lenguajeDestino;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
