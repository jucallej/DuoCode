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
 * @author Johana
 */
@XmlRootElement
public class Favoritos {
    @XmlList
    private List<Favorito> favoritos;
    
    public Favoritos() {
    }

    public Favoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }
}