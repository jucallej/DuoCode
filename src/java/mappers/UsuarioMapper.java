/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import modelo.Usuario;

/**
 *
 * @author jcarlos
 */
public class UsuarioMapper extends AbstractMapper<Usuario, Integer>{

    public UsuarioMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "usuario";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"ID", "nick", "correo","pass", "rol"};
    }

    @Override
    protected Object[] serializeObject(Usuario object) {
        return new Object[]{object.getId(), object.getNick(), object.getCorreo(), object.getPass(), object.getRol()};
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
    protected Usuario buildObject(ResultSet rs) throws SQLException {
        return new Usuario(rs.getInt("ID"), rs.getString("nick"), rs.getString("correo"), rs.getString("pass"), rs.getShort("rol"));
    }
    
    @Override
    protected Integer getKey(Usuario object) {
        return object.getId();
    }

}
