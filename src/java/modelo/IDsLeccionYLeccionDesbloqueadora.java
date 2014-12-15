/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class IDsLeccionYLeccionDesbloqueadora {    
    private int idLeccion;
    
    private int idLeccionDesbloqueadora;


    public IDsLeccionYLeccionDesbloqueadora() {
    }

    public IDsLeccionYLeccionDesbloqueadora(int idLeccion, int idLeccionDesbloqueadora) {
        this.idLeccion = idLeccion;
        this.idLeccionDesbloqueadora = idLeccionDesbloqueadora;
    }

    public int getIdLeccion() {
        return idLeccion;
    }

    public int getIdLeccionDesbloqueadora() {
        return idLeccionDesbloqueadora;
    }
}
