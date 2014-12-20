/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import utilidades.DatosFijos;


@XmlRootElement
public class Lecciones {
    @XmlElement(name="lecciones")
    private List<String> lecciones;
    
    public Lecciones() {
    }

    public Lecciones(List<Leccion> lecciones) {
        this.lecciones = new ArrayList<>();
        for (Leccion leccion: lecciones){
            this.lecciones.add(DatosFijos.RUTA_LECCIONES+leccion.getId());
        }
    }
    
    public void setLeccionesRequisitos(List<IDsLeccionYLeccionDesbloqueadora> requisitos){
        this.lecciones = new ArrayList<>();
        for (IDsLeccionYLeccionDesbloqueadora requisito: requisitos){
            this.lecciones.add(DatosFijos.RUTA_LECCIONES+requisito.getIdLeccionDesbloqueadora());
        }
    }

    public List<String> getLecciones() {
        return lecciones;
    }
}
