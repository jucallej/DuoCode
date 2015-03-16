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
public class Ruta {  
    @XmlElement(name="ruta")
    private String ruta;

     public Ruta() {
    }
     
    public Ruta(String nombre) {
        this.ruta = nombre;
    }

    public String geRuta() {
        return ruta;
    }
}
