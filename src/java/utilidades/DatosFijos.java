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
public final class DatosFijos {
    public static final String JdbcUrl = "jdbc:mysql://localhost/Duocode";
    public static final String USER = "root";
    public static final String PASS = "";
    
    public static final int NUM_MAX_EJ_POR_LECCION = 10; 
    public static final int UMBRAL_VALIDO_EJ = 7;
    public static final int VIDAS = 3;
    
    private static final String RUTA_ROOT = "https://localhost:8443/DuoCode/rest/";
    public static final String RUTA_EJERCICIOS = RUTA_ROOT+"ejercicios/";
    public static final String RUTA_TEMAS = RUTA_ROOT+"temas/";
    public static final String RUTA_ENUNCIADOS = RUTA_ROOT+"enunciados/";
    public static final String RUTA_LECCIONES = RUTA_ROOT+"lecciones/";
    public static final String RUTA_USUARIOS = RUTA_ROOT+"usuarios/";
    public static final String RUTA_CANDIDATOS = RUTA_ROOT+"candidatos/";
    
    public static final int AcquireRetryAttempts = 1;
    public static final int AcquireRetryDelay = 1;
    
    public static final boolean BreakAfterAcquireFailure = false;
    
    public static final String URL_COMPROBADOR_GOOGLE = "https://www.googleapis.com/userinfo/v2/me";
    public static String URL_COMPROBADOR_FACEBOOK = "https://graph.facebook.com/v2.3/me/?fields=id,name,picture";
}
