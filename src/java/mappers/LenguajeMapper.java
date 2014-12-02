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
public class LenguajeMapper extends AbstractMapper <Lenguaje, Integer>{

    public LenguajeMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "lenguaje";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"ID", "nombre"};
    }

    @Override
    protected Object[] serializeObject(Lenguaje object) {
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
    protected Lenguaje buildObject(ResultSet rs) throws SQLException {
        return new Lenguaje(rs.getInt("ID"), rs.getString("nombre"));
    }

    @Override
    protected Integer getKey(Lenguaje object) {
        return object.getId();
    }

    @Override
    public int insert(Lenguaje object) {
        int autoincrement = super.insert(object);
        object.setId(autoincrement);
        return autoincrement;
    }
    
    
    
}
