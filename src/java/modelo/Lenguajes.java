/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julián
 */
@XmlRootElement
public class Lenguajes {
    @XmlList
    private List<Lenguaje> lenguajes;

    public Lenguajes() {
    }
    
    public Lenguajes(List<Lenguaje> lenguajes) {
        this.lenguajes = lenguajes;
    }
}
