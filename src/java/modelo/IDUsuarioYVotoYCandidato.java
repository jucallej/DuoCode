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
public class IDUsuarioYVotoYCandidato {
    @XmlElement(name="votar")
    private IDUsuarioYVoto iDUsuarioYVoto;
    @XmlElement(name="candidato")
    private Candidato candidato;

    public IDUsuarioYVotoYCandidato() {
    }

    public IDUsuarioYVotoYCandidato(IDUsuarioYVoto iDUsuarioYVoto, Candidato candidato) {
        this.iDUsuarioYVoto = iDUsuarioYVoto;
        this.candidato = candidato;
    }

    public IDUsuarioYVoto getiDUsuarioYVoto() {
        return iDUsuarioYVoto;
    }

    public Candidato getCandidato() {
        return candidato;
    }
}
