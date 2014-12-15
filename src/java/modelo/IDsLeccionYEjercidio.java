/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class IDsLeccionYEjercidio {    
    private int idLeccion;
    
    private int idEjercicio;


    public IDsLeccionYEjercidio() {
    }

    public IDsLeccionYEjercidio(int idLeccion, int idEjercicio) {
        this.idLeccion = idLeccion;
        this.idEjercicio = idEjercicio;
    }

    public int getIdLeccion() {
        return idLeccion;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }
}
