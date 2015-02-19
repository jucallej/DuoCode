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
import modelo.Enunciado;

/**
 *
 * @author Julián
 */
public class EnunciadoMapper extends AbstractMapper <Enunciado, Integer>{

    public EnunciadoMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "enunciado";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"ID", "texto", "fecha", "idEjercicio", "lenguaje"};
    }

    @Override
    protected Object[] serializeObject(Enunciado object) {
        return new Object[]{object.getIdEnunciado(), object.getCodigo(), object.getFechaCreacion(), object.getIdEjercicio(), object.getLenguaje()};
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
    protected Enunciado buildObject(ResultSet rs) throws SQLException {
        return new Enunciado(rs.getInt("ID"), rs.getString("texto"), rs.getInt("idEjercicio"), rs.getString("lenguaje"), rs.getTime("fecha") );
    }

    @Override
    protected Integer getKey(Enunciado object) {
        return object.getIdEnunciado();
    }
    
    public List<Enunciado> getEnunciadosDeUnEjercicio(int idEjercicio){
        return this.findByConditions(new QueryCondition[]{new QueryCondition("idEjercicio", Operator.EQ, idEjercicio)});
    }
}
