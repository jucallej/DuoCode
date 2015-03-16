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
public class ErrorYPuntuacion {
    @XmlElement(name="error")
    private String error;
    @XmlElement(name="puntuacion")
    private int puntuacion;

    public ErrorYPuntuacion() {
    }

    public ErrorYPuntuacion(String error, int puntuacion) {
        this.error = error;
        this.puntuacion = puntuacion;
    }
    
    public ErrorYPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
        if (puntuacion >=0)
            this.error = "no";
        else
            this.error = "si";
    }

    public String getError() {
        return error;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
}
