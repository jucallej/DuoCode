/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
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
    
    @XmlElement(name="votos")
    private List<Integer> votos;

    
     public Candidato() {
    }
     
    public Candidato(int id, String codigo, Date fecha, int estado, int gestionadoPor, int idUsuario, int idEjercicio, String lenguajeOrigen, String lenguajeDestino) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.estado = estado;
        if (gestionadoPor >0)
            this.gestionadoPor = gestionadoPor;
        else 
            this.gestionadoPor = -1;
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

    public void setGestionadoPor(int gestionadoPor) {
        this.gestionadoPor = gestionadoPor;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setVotos(List<UsuarioVotaCandidato> votosDeUnCandidato) {
        this.votos = new ArrayList<>();
        
        for (UsuarioVotaCandidato voto: votosDeUnCandidato){
            this.votos.add(voto.getIdUsuario());
        }
    }
   
}
