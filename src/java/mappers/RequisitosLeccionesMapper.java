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
import modelo.IDsLeccionYLeccionDesbloqueadora;


public class RequisitosLeccionesMapper extends AbstractMapper <IDsLeccionYLeccionDesbloqueadora, IDsLeccionYLeccionDesbloqueadora>{
    public RequisitosLeccionesMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "desbloqueadapor";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"idLeccion", "desbloqueadaPor"};
    }

    @Override
    protected Object[] serializeObject(IDsLeccionYLeccionDesbloqueadora object) {
        return new Object[]{object.getIdLeccion(), object.getIdLeccionDesbloqueadora()};
    }

    @Override
    protected String[] getKeyColumnNames() {
        return new String[] {"idLeccion", "desbloqueadaPor"};
    }

    @Override
    protected Object[] serializeKey(IDsLeccionYLeccionDesbloqueadora key) {
        return new Object[]{key.getIdLeccion(), key.getIdLeccionDesbloqueadora()};
    }

    @Override
    protected IDsLeccionYLeccionDesbloqueadora buildObject(ResultSet rs) throws SQLException {
        return new IDsLeccionYLeccionDesbloqueadora(rs.getInt("idLeccion"), rs.getInt("desbloqueadaPor"));
    }
    
    @Override
    protected IDsLeccionYLeccionDesbloqueadora getKey(IDsLeccionYLeccionDesbloqueadora object) {
        return object;
    }
    
    public List<IDsLeccionYLeccionDesbloqueadora> getIDsLeccionYDesbloqueadorasConIDELeccion(int idLeccion){
        return this.findByConditions(new QueryCondition[]{new QueryCondition("idLeccion", Operator.EQ, idLeccion)});
    }
}
