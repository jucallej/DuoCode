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
    private int voto;
    public UsuarioVotaCandidato() {
    }

    public UsuarioVotaCandidato(int idUsuario, int idCandidato, int voto) {
        this.idUsuario = idUsuario;
        this.idCandidato = idCandidato;
        this.voto = voto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    
    public int getIdCandidato() {
        return idCandidato;
    }

    public int getVoto() {
        return voto;
    }    
}


