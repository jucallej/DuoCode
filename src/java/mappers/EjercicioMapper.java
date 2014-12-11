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
public class EjercicioMapper extends AbstractMapper <Integer, Integer>{

    public EjercicioMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "ejercicio";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"ID"};
    }

    @Override
    protected Object[] serializeObject(Integer object) {
        return new Object[]{object};
    }

    @Override
    protected String[] getKeyColumnNames() {
        return new String[] {"ID"};
    }

    @Override
    protected Object[] serializeKey(Integer key) {
        return new Object[]{key};
    }

    @Override
    protected Integer buildObject(ResultSet rs) throws SQLException {
        return rs.getInt("ID");
    }

    @Override
    protected Integer getKey(Integer object) {
        return object;
    }
}
