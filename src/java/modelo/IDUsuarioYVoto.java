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
 * @author Julián
 */
@XmlRootElement
public class IDUsuarioYVoto {
    @XmlElement(name="idUsuario")
    private int idUsuario;
    @XmlElement(name="voto")
    private int voto;

    public IDUsuarioYVoto() {
    }

    public IDUsuarioYVoto(int idUsuario, int voto) {
        this.idUsuario = idUsuario;
        this.voto = voto;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public int getVoto() {
        return voto;
    }
    
}
