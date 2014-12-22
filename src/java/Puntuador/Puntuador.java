/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Puntuador;

import modelo.Envio;

/**
 *
 * @author Julián
 */
public class Puntuador {
    
    //Tambien cambia la puntuacion internamente del envio
    public static int puntuar(Envio envio){
        //Aquí se conectaría con el otro grupo
        int puntuacion = (int) ((Math.random()*10)+0); //Maximo 10 minimo 0
        
        envio.setPuntuacion(puntuacion);
        
        return envio.getPuntuacion();
    }
    
    /*
    Hay que ver a partir de que se le da a un usuario la leccion como completada al comprobarlo en envio 
    (y si cuando el usuario ha fallado alguna pregunta, se le debe exigir que acierte más para compensar 
    (o que acierte X seguidas, y que no valga que haya tenido un fallo en medio (o hasta 3 fallos como en duolingo)) ... )
    */
    
    
    
    /* ¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡NUEVO!!!!!!!!!!!!!!!!!!
    Lo de que no devolvia Error era porque cogia la clase error de java (la de las expcepciones) en vez de la nuestra. Ya lo he cambiado (creo que no me he saltado nada).

    Falta la tabla leccionesCompletadas o similar y un atributo en usuariovotacandidato para saber si el voto es positivo o negativo no?

    Falta como hacer y borrar favoritos en los requisitos. En un recurso nuevo (favoritos) un post para crearlos y un delete?¿ Que así se puede hacer fácil.

    */
}
