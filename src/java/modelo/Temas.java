/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import utilidades.DatosFijos;

/**
 *
 * @author Johana
 */
@XmlRootElement
public class Temas {
    @XmlList
    private List<Ruta> temas;
    //private List<String> temas;
    
    public Temas() {
    }

    public Temas(List<Tema> temas) {
        this.temas = new ArrayList<>();
        for (Tema tema: temas){
            this.temas.add(new Ruta(DatosFijos.RUTA_TEMAS+tema.getId()));
            //this.temas.add(DatosFijos.RUTA_TEMAS+tema.getId());

        }
    }
    
}
