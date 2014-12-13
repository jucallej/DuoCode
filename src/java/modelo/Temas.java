/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Johana
 */
@XmlRootElement
public class Temas {
    @XmlList
    private List<Tema> temas;
    
    public Temas() {
    }

    public Temas(List<Tema> temas) {
        this.temas = new ArrayList<>();
        for (Tema tema: temas){
            this.temas.add(new Tema(tema.getId(), tema.getOrden(), tema.getTitulo(), tema.getDescripcion()));
        }
    }
    
}
