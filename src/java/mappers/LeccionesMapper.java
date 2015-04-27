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
import modelo.Leccion;


public class LeccionesMapper extends AbstractMapper <Leccion, Integer>{
    public LeccionesMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "leccion";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"ID", "orden", "titulo","descripcion","explicacion", "idTema"};
    }

    @Override
    protected Object[] serializeObject(Leccion object) {
        return new Object[]{object.getId(), object.getOrden(), object.getTitulo(), object.getDescripcion(), object.getExplicacion(), object.getIdTema()};
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
    protected Leccion buildObject(ResultSet rs) throws SQLException {
        return new Leccion(rs.getInt("ID"), rs.getString("titulo"), rs.getString("descripcion"), rs.getString("explicacion"), rs.getInt("orden"), rs.getInt("idTema"));
    }
    
    @Override
    protected Integer getKey(Leccion object) {
        return object.getId();
    }
    
    public List<Leccion> getLeccionesDeUnTema(int idTema){
        return this.findByConditions(new QueryCondition[]{new QueryCondition("idTema", Operator.EQ, idTema)});
    }
}
