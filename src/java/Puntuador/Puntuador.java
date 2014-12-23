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
    
    FALLA DELETE DE FAV 
    (añadido)->> ni idea es raro, no devuleve nada, es como si no se meteira en el método. Por cierto lo de
    boolean error = favoritoMapper.delete(favorito); no estoy seguro que el delete devuelva un boolean, que modifique el insert para que devolviera el int del autoincrement
    y queria modificar el delete, pero no se si lo llegué a hacer
    
    Lo de que fallava lo de candidatos era por un metodo en UsuarioVotaCandidatoMapper
    @Override
    protected Object[] serializeKey(UsuarioVotaCandidato key) {
        return new Object[]{key.getIdUsuario(), key.getIdCandidato()}; // y estaba  return new Object[]{key}; 
    }

    */
}
