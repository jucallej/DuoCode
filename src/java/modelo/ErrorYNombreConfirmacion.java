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
public class ErrorYNombreConfirmacion {
    @XmlElement(name="error")
    private String error;
    @XmlElement(name="nombreConfirmacion")
    private String nombreConfirmacion;

    public ErrorYNombreConfirmacion() {
    }

    public ErrorYNombreConfirmacion(String error, String nombreConfirmacion) {
        this.error = error;
        this.nombreConfirmacion = nombreConfirmacion;
    }
    
    public ErrorYNombreConfirmacion(String nombreConfirmacion) {
        this.nombreConfirmacion = nombreConfirmacion;
        if (!nombreConfirmacion.equalsIgnoreCase(""))
            this.error = "no";
        else
            this.error = "si";
    }

    public ErrorYNombreConfirmacion(int posibleError, String lenguaje) {
        if (posibleError >=0){
            this.error = "no";
            this.nombreConfirmacion = lenguaje;
        }
        else{
            this.error = "si";
            this.nombreConfirmacion = "";
        }
    }

    public String getError() {
        return error;
    }

    public String getNombreConfirmacion() {
        return nombreConfirmacion;
    }
}
