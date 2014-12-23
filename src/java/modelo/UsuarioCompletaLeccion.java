/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julián
 */
@XmlRootElement
public class UsuarioCompletaLeccion {  
    private int idUsuario;
    
    private int idLeccion;

    public UsuarioCompletaLeccion() {
    }

    public UsuarioCompletaLeccion(int idUsuario, int idLeccion) {
        this.idUsuario = idUsuario;
        this.idLeccion = idLeccion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdLeccion() {
        return idLeccion;
    }
}
