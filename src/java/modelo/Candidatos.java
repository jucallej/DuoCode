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
 * @author Johana
 */
@XmlRootElement
public class Candidatos {
    @XmlElement(name="candidatos")
    private List<String> candidatos;
    
    public Candidatos() {
    }

    public Candidatos(List<Candidato> candidatos) {
        this.candidatos = new ArrayList<>();
        for (Candidato candidato: candidatos){
            this.candidatos.add(DatosFijos.RUTA_CANDIDATOS+candidato.getId());
        }
    }
    
}
