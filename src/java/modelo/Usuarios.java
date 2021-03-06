/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import utilidades.DatosFijos;

/**
 *
 * @author jcarlos
 */
public class Usuarios {
    @XmlElement(name="usuarios")
    private List<String> usuarios;

    public Usuarios() {
    }

    public Usuarios(List<Usuario> usuarios) {
        this.usuarios = new ArrayList<>();
        for (Usuario usuario: usuarios){
            this.usuarios.add(DatosFijos.RUTA_USUARIOS+usuario.getId());
        }
    }
}
