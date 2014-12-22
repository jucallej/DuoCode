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
public class VotoIDUsuario {
    @XmlElement(name="idUsuario")
    private int idUsuario;
    
    @XmlElement(name="voto")
    private String voto;

    public VotoIDUsuario() {
    }

    public VotoIDUsuario(int idUsuario, String voto) {
        this.idUsuario = idUsuario;
        this.voto = voto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getVoto() {
        return voto;
    }
}
