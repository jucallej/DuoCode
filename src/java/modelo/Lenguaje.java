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
public class Lenguaje {  
    @XmlElement(name="nombre")
    private String nombre;

     public Lenguaje() {
    }
     
    public Lenguaje(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
