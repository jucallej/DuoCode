/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import utilidades.DatosFijos;

/**
 *
 * @author Johana
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Temas {
    //@XmlList
    //private List<Ruta> temas;
    @XmlElement(name="temas")
    private List<String> temas;
    
    public Temas() {
    }

    public Temas(List<Tema> temas) {
        this.temas = new ArrayList<>();
        for (Tema tema: temas){
            //this.temas.add(new Ruta(DatosFijos.RUTA_TEMAS+tema.getId()));
            this.temas.add(DatosFijos.RUTA_TEMAS+tema.getId());

        }
    }
}
