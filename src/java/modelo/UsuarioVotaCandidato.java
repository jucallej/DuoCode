/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Johana
 */
public class UsuarioVotaCandidato {
    private int idUsuario;
    
    private int idCandidato;
    
    public UsuarioVotaCandidato() {
    }

    public UsuarioVotaCandidato(int idUsuario, int idCandidato) {
        this.idUsuario = idUsuario;
        this.idCandidato = idCandidato;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    
    public int getIdCandidato() {
        return idCandidato;
    }
    
}

