/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import utilidades.DatosFijos;


@XmlRootElement
public class Lecciones {
    @XmlList
    private List<Ruta> lecciones;
    private List <Integer> intLecciones; //Para insertarlos en leccionResource, para lo demás no se usan
    
    public Lecciones() {
    }

    public Lecciones(List<Leccion> lecciones) {
        this.lecciones = new ArrayList<>();
        intLecciones = new ArrayList<>();
        for (Leccion leccion: lecciones){
            this.lecciones.add(new Ruta(DatosFijos.RUTA_LECCIONES+leccion.getId()));
            this.intLecciones.add(leccion.getId());
        }
    }
    
    public void setLeccionesRequisitos(List<IDsLeccionYLeccionDesbloqueadora> requisitos){
        this.lecciones = new ArrayList<>();
        intLecciones = new ArrayList<>();
        for (IDsLeccionYLeccionDesbloqueadora requisito: requisitos){
            this.lecciones.add(new Ruta(DatosFijos.RUTA_LECCIONES+requisito.getIdLeccionDesbloqueadora()));
            this.intLecciones.add(requisito.getIdLeccionDesbloqueadora());
        }
    }

    public List<Integer> getIntLecciones() {
        return intLecciones;
    }

    public List<Ruta> getLecciones() {
        return lecciones;
    }

    //Si ya existe lo borra de lecciones (no hace falta añadirlo, y sino lo mantiende y devuelve false)
    public boolean contienen(Integer leccionesDesbloqueadorasExistente) {
        boolean encontrado = false;
        Iterator<Ruta> iterador = this.lecciones.iterator();
        
        while (!encontrado && iterador.hasNext()){
            Ruta ruta = iterador.next();
            System.out.println(Integer.parseInt(ruta.geRuta())+"leccionExistente"+leccionesDesbloqueadorasExistente);
            if (Integer.parseInt(ruta.geRuta()) == leccionesDesbloqueadorasExistente){
                encontrado = true;
                iterador.remove();
            }
        }
        return encontrado;
    }
}
