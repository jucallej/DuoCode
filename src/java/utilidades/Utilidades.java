/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;

/**
 *
 * @author Julián
 */
public final class Utilidades {
    /**
     * Comprueba si ComboPooledDataSource es null, y si lo es crea una nueva instancia.
     * Si no usramos esto, por cada petición crea una nueva conexión y eventualmente falla porque no puede más.
     * @param cpds a comprobar si es null
     */
    public static ComboPooledDataSource checkPoolNull(ComboPooledDataSource cpds){
        if (cpds == null){
            cpds = new ComboPooledDataSource();
            try {
                    cpds.setDriverClass("org.gjt.mm.mysql.Driver");
            } catch (PropertyVetoException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            cpds.setJdbcUrl(DatosFijos.JdbcUrl);
            cpds.setUser(DatosFijos.USER);
            cpds.setPassword(DatosFijos.PASS);
            cpds.setAcquireRetryAttempts(DatosFijos.AcquireRetryAttempts);
            cpds.setAcquireRetryDelay(DatosFijos.AcquireRetryDelay);
            cpds.setBreakAfterAcquireFailure(DatosFijos.BreakAfterAcquireFailure);
        }
        return cpds;
    }
    
}
