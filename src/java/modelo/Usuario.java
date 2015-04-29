/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcarlos
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Usuario {
    
    @XmlElement(name="ID")
    private int id;
    ////////////////////////////////////////////////////////////////////////////
    @XmlElement(name="IDGoogle")
    private String idGoogle;
    
    @XmlElement(name="IDFacebook")
    private String idFacebook;
    
    @XmlElement(name="rol")
    private short rol;
    
    @XmlElement(name="picture")
    private String picture;
    
    @XmlElement(name="name")
    private String name;
    
    @XmlElement(name="leccionesCompletadas")
    private List<UsuarioCompletaLeccion> leccionesCompletadas;
    
    @XmlList
    private List<Favorito> favoritos;
    
    @XmlList
    private List<Envio> historialEjercicios;
    
    @XmlList
    private List<Candidato> candidatosPropuestos;
    
    @XmlList
    private List<UsuarioVotaCandidato> votosDeUnUsuario;
    
    public Usuario() {
    }

    public Usuario(int id, String idGoogle, String idFacebook, short rol) {
        this.id = id;
        this.idGoogle = idGoogle;
        this.idFacebook = idFacebook;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getIdGoogle(){
        return this.idGoogle;
    }

    public short getRol() {
        return rol;
    }

    public void setRol(short rol) {
        this.rol = rol;
    }

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
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

    public void setLeccionesTerminadas(List<UsuarioCompletaLeccion> usuarioCompletaLeccionDeUnUsuario) {
        this.leccionesCompletadas = usuarioCompletaLeccionDeUnUsuario;
       /* leccionesCompletadas = new ArrayList<>();
        for (UsuarioCompletaLeccion leccion: usuarioCompletaLeccionDeUnUsuario){
            leccionesCompletadas.add(leccion.getIdLeccion());
        }*/
    }

    public void setVotosCandidatos(List<UsuarioVotaCandidato> votosDeUnUsuario) {
        this.votosDeUnUsuario = votosDeUnUsuario;
    }

    public List<UsuarioVotaCandidato> getVotosDeUnUsuario() {
        return votosDeUnUsuario;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
