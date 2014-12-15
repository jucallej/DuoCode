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

/**
 *
 * @author Julián
 */
@XmlRootElement
public class Ejercicios {
    @XmlList
    private List<Ruta> ejercicios;
    private List <Integer> intEjercicios; //Para insertarlos en leccionResource, para lo demás no se usan

    public Ejercicios() {
    }

    public Ejercicios(List<Integer> ejercicios) {
        this.ejercicios = new ArrayList<>();
        intEjercicios = new ArrayList<>();
        for (Integer ejercicio: ejercicios){
            this.ejercicios.add(new Ruta(DatosFijos.RUTA_EJERCICIOS+ejercicio));
            this.intEjercicios.add(ejercicio);
        }
    }
    
    public void setEjerciciosATravesDeIDsLeccionYEjercidio(List<IDsLeccionYEjercidio> ejerciciosDeLaLeccion){
        this.ejercicios = new ArrayList<>();
        intEjercicios = new ArrayList<>();
        for (IDsLeccionYEjercidio ejercicio: ejerciciosDeLaLeccion){
            this.ejercicios.add(new Ruta(DatosFijos.RUTA_EJERCICIOS+ejercicio.getIdEjercicio()));
            this.intEjercicios.add(ejercicio.getIdEjercicio());
        }
    }

    public List<Integer> getIntEjercicios() {
        return intEjercicios;
    }

    public List<Ruta> getEjercicios() {
        return ejercicios;
    } 

    //Si ya existe lo borra de ejercicios (no hace falta añadirlo, y sino lo mantiende y devuelve false)
    public boolean contienen(Integer ejerciciosExistente) {
        boolean encontrado = false;
        Iterator<Ruta> iterador = this.ejercicios.iterator();
        
        while (!encontrado && iterador.hasNext()){
            Ruta ruta = iterador.next();
            
            if (Integer.parseInt(ruta.geRuta()) == ejerciciosExistente){
                encontrado = true;
                iterador.remove();
            }
        }
        return encontrado;
    }
}
