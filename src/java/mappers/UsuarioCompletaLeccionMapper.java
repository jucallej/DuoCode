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
import modelo.UsuarioCompletaLeccion;

/**
 *
 * @author Julián
 */
public class UsuarioCompletaLeccionMapper extends AbstractMapper <UsuarioCompletaLeccion, UsuarioCompletaLeccion>{

    public UsuarioCompletaLeccionMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "usuariocompletaleccion";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"idUsuario", "idLeccion", "lenguaje"};
    }

    @Override
    protected Object[] serializeObject(UsuarioCompletaLeccion object) {
        return new Object[]{object.getIdUsuario(), object.getIdLeccion(), object.getLenguaje()};
    }

    @Override
    protected String[] getKeyColumnNames() {
        return new String[] {"idUsuario", "idLeccion", "lenguaje"};
    }

    @Override
    protected Object[] serializeKey(UsuarioCompletaLeccion key) {
        return new Object[]{key};
    }

    @Override
    protected UsuarioCompletaLeccion buildObject(ResultSet rs) throws SQLException {
        return new UsuarioCompletaLeccion(rs.getInt("idUsuario"), rs.getInt("idLeccion"), rs.getString("lenguaje"));
    }

    @Override
    protected UsuarioCompletaLeccion getKey(UsuarioCompletaLeccion object) {
        return object;
    }
    
    public List<UsuarioCompletaLeccion> getUsuarioCompletaLeccionDeUnUsuario(int idUsuario){
        return this.findByConditions(new QueryCondition[]{new QueryCondition("idUsuario", Operator.EQ, idUsuario)});
    }
}
