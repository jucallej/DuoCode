/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
        return new String[] {"ID", "IDGoogle", "rol"};
    }

    @Override
    protected Object[] serializeObject(Usuario object) {
        return new Object[]{object.getId(), object.getIdGoogle(), object.getRol()};
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
        return new Usuario(rs.getInt("ID"), rs.getString("IDGoogle"), rs.getShort("rol"));
    }
    
    @Override
    protected Integer getKey(Usuario object) {
        return object.getId();
    }
    
    public Usuario findUsuarioIDGoogle(String IDGoogle){
        
        List <Usuario> usuarioIDGoogle = this.findByConditions(new QueryCondition[]{new QueryCondition("IDGoogle", Operator.EQ, IDGoogle)});
        Usuario usuarioBuscado = null;
        if (usuarioIDGoogle.size() == 1) usuarioBuscado = usuarioIDGoogle.get(0);
        
        return usuarioBuscado;
    }

}
