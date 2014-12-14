/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import modelo.Tema;

/**
 *
 * @author Johana
 */
public class TemaMapper extends AbstractMapper <Tema, Integer>{
    public TemaMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "tema";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"ID", "orden", "titulo","descripcion"};
    }

    @Override
    protected Object[] serializeObject(Tema object) {
        return new Object[]{object.getId(), object.getOrden(), object.getTitulo(), object.getDescripcion()};
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
    protected Tema buildObject(ResultSet rs) throws SQLException {
        return new Tema(rs.getInt("ID"), rs.getInt("orden"), rs.getString("titulo"), rs.getString("descripcion"));
    }
    
    @Override
    protected Integer getKey(Tema object) {
        return object.getId();
    }

}
