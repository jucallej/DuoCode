/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import modelo.Ejercicio;

/**
 *
 * @author Julián
 */
public class EjercicioMapper extends AbstractMapper <Ejercicio, Integer>{

    public EjercicioMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "ejercicio";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"ID","nombre"};
    }

    @Override
    protected Object[] serializeObject(Ejercicio object) {
        return new Object[]{object.getId(), object.getNombre()};
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
    protected Ejercicio buildObject(ResultSet rs) throws SQLException {
        return new Ejercicio(rs.getInt("ID"), rs.getString("nombre"));
    }

    @Override
    protected Integer getKey(Ejercicio object) {
        return object.getId();
    }
}
