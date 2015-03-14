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

/**
 *
 * @author Julián
 */
@XmlRootElement
public class Ejercicios {
    @XmlElement(name="ejercicios")
    private List<String> ejercicios;
    //private List <Integer> intEjercicios; //Para insertarlos en leccionResource, para lo demás no se usan

    public Ejercicios() {
    }

    public Ejercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = new ArrayList<>();
        //intEjercicios = new ArrayList<>();
        for (Ejercicio ejercicio: ejercicios){
            this.ejercicios.add(DatosFijos.RUTA_EJERCICIOS+ejercicio.getId());
            //this.intEjercicios.add(ejercicio);
        }
    }
    
    public void setEjerciciosATravesDeIDsLeccionYEjercidio(List<IDsLeccionYEjercidio> ejerciciosDeLaLeccion){
        this.ejercicios = new ArrayList<>();
        //intEjercicios = new ArrayList<>();
        for (IDsLeccionYEjercidio ejercicio: ejerciciosDeLaLeccion){
            this.ejercicios.add(DatosFijos.RUTA_EJERCICIOS+ejercicio.getIdEjercicio());
            //this.intEjercicios.add(ejercicio.getIdEjercicio());
        }
    }
/*
    public List<Integer> getIntEjercicios() {
        return intEjercicios;
    }
*/
    public List<String> getEjercicios() {
        return ejercicios;
    } 
}
