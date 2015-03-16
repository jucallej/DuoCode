/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Johana
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Favoritos {
    @XmlList
    private List<Favorito> favoritos;
    
    public Favoritos() {
    }

    public Favoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }

    /**
     * @return Un int con el idUsuario si todos son iguales, -1 si hay error, y -2 su no hay datos
     */
    public int comprobarQueElUsuarioSiempreEsElMismo(int usuario) {
        int idQueDebeSerSiempreIgual = -1;
        
        if(favoritos.size()>0){
            idQueDebeSerSiempreIgual = usuario;
            Iterator<Favorito> iterador = favoritos.iterator();
            while (idQueDebeSerSiempreIgual != -1 && iterador.hasNext()){
                if (iterador.next().getIdUsuario() != idQueDebeSerSiempreIgual)
                    idQueDebeSerSiempreIgual=-1;
            }
        }
        else if (favoritos.isEmpty()) idQueDebeSerSiempreIgual = -2;
        
        return idQueDebeSerSiempreIgual;
    }

    public List<Favorito> getFavoritos() {
        return favoritos;
    }
}