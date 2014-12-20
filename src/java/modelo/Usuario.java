/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcarlos
 */
@XmlRootElement
public class Usuario {
    
    @XmlElement(name="ID")
    private int id;
    
    @XmlElement(name="nick")
    private String nick;
    
    @XmlElement(name="correo")
    private String correo;
    
    @XmlElement(name="pass")
    private String pass;
    
    @XmlElement(name="rol")
    private short rol;
    
    @XmlList
    private List<Favorito> favoritos;
    
    @XmlList
    private List<Envio> historialEjercicios;
    
    @XmlList
    private List<Candidato> candidatosPropuestos;
    
    public Usuario() {
    }

    public Usuario(int id, String nick, String correo, String pass, short rol) {
        this.id = id;
        this.nick = nick;
        this.correo = correo;
        this.pass = pass;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public short getRol() {
        return rol;
    }

    public void setRol(short rol) {
        this.rol = rol;
    }

    public List<Envio> getHistorialEjercicios() {
        return historialEjercicios;
    }

    public void setHistorialEjercicios(List<Envio> envios) {
        this.historialEjercicios = envios;
    }

    public List<Candidato> getCandidatosPropuestos() {
        return candidatosPropuestos;
    }

    public void setCandidatosPropuestos(List<Candidato> candidatosPropuestos) {
        this.candidatosPropuestos = candidatosPropuestos;
    }

    public List<Favorito> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }
}
