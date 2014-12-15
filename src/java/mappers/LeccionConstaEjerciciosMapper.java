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
import modelo.IDsLeccionYEjercidio;
import modelo.Leccion;


public class LeccionConstaEjerciciosMapper extends AbstractMapper <IDsLeccionYEjercidio, IDsLeccionYEjercidio>{
    public LeccionConstaEjerciciosMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "leccionconstaejercicio";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"idLeccion", "idEjercicio"};
    }

    @Override
    protected Object[] serializeObject(IDsLeccionYEjercidio object) {
        return new Object[]{object.getIdLeccion(), object.getIdEjercicio()};
    }

    @Override
    protected String[] getKeyColumnNames() {
        return new String[] {"idLeccion", "idEjercicio"};
    }

    @Override
    protected Object[] serializeKey(IDsLeccionYEjercidio key) {
        return new Object[]{key.getIdLeccion(), key.getIdEjercicio()};
    }

    @Override
    protected IDsLeccionYEjercidio buildObject(ResultSet rs) throws SQLException {
        return new IDsLeccionYEjercidio(rs.getInt("idLeccion"), rs.getInt("idEjercicio"));
    }
    
    @Override
    protected IDsLeccionYEjercidio getKey(IDsLeccionYEjercidio object) {
        return object;
    }
    
    public List<IDsLeccionYEjercidio> getIDsLeccionYEjercidioConIDELeccion(int idLeccion){
        return this.findByConditions(new QueryCondition[]{new QueryCondition("idLeccion", Operator.EQ, idLeccion)});
    }
}
