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
import utilidades.DatosFijos;

/**
 *
 * @author Julián
 */
@XmlRootElement
public class Enunciados {
    @XmlElement(name="enunciados")
    private List<String> enunciados;

    public Enunciados() {
    }

    public Enunciados(List<Enunciado> enunciados) {
        this.enunciados = new ArrayList<>();
        for (Enunciado enunciado: enunciados){
            this.enunciados.add(DatosFijos.RUTA_ENUNCIADOS+enunciado.getIdEnunciado());
        }
    }
}
