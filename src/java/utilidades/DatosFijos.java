/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author Julián
 */
public class DatosFijos {
    public static final String JdbcUrl = "jdbc:mysql://localhost/Duocode";
    public static final String USER = "root";
    public static final String PASS = "";
    
    public static final String RUTA_EJERCICIOS = "http://localhost:8080/DuoCode/rest/ejercicios/";
    public static final String RUTA_TEMAS = "http://localhost:8080/DuoCode/rest/temas/";
    public static final String RUTA_ENUNCIADOS = "http://localhost:8080/DuoCode/rest/enunciados/";
    public static final String RUTA_LECCIONES = "http://localhost:8080/DuoCode/rest/leciones/";
    public static final String RUTA_USUARIOS = "http://localhost:8080/DuoCode/rest/usuarios/";
    
    public static final int AcquireRetryAttempts = 1;
    public static final int AcquireRetryDelay = 1;
    
    public static final boolean BreakAfterAcquireFailure = true;

}