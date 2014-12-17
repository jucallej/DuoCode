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
 * @author Johana
 */
@XmlRootElement
public class Candidato {
    @XmlElement(name="id")
    private int id;
    
    @XmlElement(name="codigo")
    private String codigo;
    
    @XmlElement(name="fecha")
    private Date fecha;
    
    @XmlElement(name="estado")
    private int estado;
    
    @XmlElement(name="gestionadoPor")
    private int gestionadoPor;

    @XmlElement(name="idUsuario")
    private int idUsuario;

    @XmlElement(name="idEjercicio")
    private int idEjercicio;

    @XmlElement(name="lenguajeOrigen")
    private String lenguajeOrigen;

    @XmlElement(name="lenguajeDestino")
    private String lenguajeDestino;

    
     public Candidato() {
    }
     
    public Candidato(int id, String codigo, Date fecha, int estado, int gestionadoPor, int idUsuario, int idEjercicio, String lenguajeOrigen, String lenguajeDestino) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.estado = estado;
        this.gestionadoPor = gestionadoPor;
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

    public int getEstado() {
        return estado;
    }
    
    public int getGestionadoPor() {
        return gestionadoPor;
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
    
    public String getlenguajeDestino() {
        return lenguajeDestino;
    }
    
    public int setId(int idCandidato) {
        return this.id = idCandidato;
    }
    
    public Date setFecha(Date fechaCandidato) {
        return this.fecha = fechaCandidato;
    }
   
}
