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
    Cuestiones, dudas etc.

1. Los objetos que se convierte a JSON si son de un solo atributo no se convierten bien (ej. return DELETE temas) (creemos que es eso)

2. Los array no conseguimos que sean solo de por ej String (al convertirse a JSON quedan mal, 
    como un atributo normal en vez de array con los strings separados por espacios (ej comentado en GET temas (hay que cambiar los comentarios))), 
    SI funcionan los array de objetos

3. Hay que ver a partir de que se le da a un usuario la leccion como completada al comprobarlo en envio 
    (y si cuando el usuario ha fallado alguna pregunta, se le debe exigir que acierte más para compensar 
    (o que acierte X seguidas, y que no valga que haya tenido un fallo en medio (o hasta 3 fallos como en duolingo)) ... )

4. Al hacer PUT de un envio lo acepta, si lo vuelves a hacer con los mismos datos 
   (por ej el usuario ha fallado y quiere ponerla bien, o algo así) da fallo de SQL. Al insertar SQL a "mano" por phpmyadmin igual. EJ:
INSERT INTO `duocode`.`envio` (`ID`, `codigo`, `fecha`, `puntuacion`, `idUsuario`, `idEjercicio`, `lenguajeOrigen`, `lenguajeDestino`) 
    VALUES (NULL, 'fefae', CURRENT_TIMESTAMP, '8', '1', '3', 'C++', 'Java')

    La primera vez bien, luego da 
#1062 - Duplicate entry 'C++' for key 'lenguajeOrigen'
    Debería dejarte no?
    */
}
