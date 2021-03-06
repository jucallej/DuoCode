/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import modelo.Lenguaje;

/**
 *
 * @author Julián
 */
public class LenguajeMapper extends AbstractMapper <String, String>{

    public LenguajeMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "lenguaje";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"nombre"};
    }

    @Override
    protected Object[] serializeObject(String object) {
        return new Object[]{object};
    }

    @Override
    protected String[] getKeyColumnNames() {
        return new String[] {"nombre"};
    }

    @Override
    protected Object[] serializeKey(String key) {
        return new Object[]{key};
    }

    @Override
    protected String buildObject(ResultSet rs) throws SQLException {
        return rs.getString("nombre");
    }

    @Override
    protected String getKey(String object) {
        return object;
    }
}
