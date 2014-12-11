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
 * @author Julián
 */
@XmlRootElement
public class Ejercicios {
    @XmlList
    private List<Ejercicio> ejercicios;

    public Ejercicios() {
    }

    public Ejercicios(List<Integer> ejercicios) {
        this.ejercicios = new ArrayList<>();
        for (Integer ejercicio: ejercicios){
            this.ejercicios.add(new Ejercicio(DatosFijos.RUTA_EJERCICIOS+ejercicio));
        }
    }
}
