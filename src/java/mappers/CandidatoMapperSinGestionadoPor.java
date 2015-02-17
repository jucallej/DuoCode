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
import modelo.Candidato;

/**
 *
 * @author Johana
 */
public class CandidatoMapperSinGestionadoPor extends AbstractMapper<Candidato, Integer>{
    public CandidatoMapperSinGestionadoPor(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "candidato";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"ID", "codigo", "fecha", "estado", "idUsuario", "idEjercicio", "lenguajeOrigen", "lenguajeDestino"};
    }

    @Override
    protected Object[] serializeObject(Candidato object) {
        return new Object[]{object.getId(), object.getCodigo(), object.getFecha(), object.getEstado(), object.getIdUsuario(), object.getIdEjercicio(), object.getLenguajeOrigen(), object.getlenguajeDestino()};
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
    protected Candidato buildObject(ResultSet rs) throws SQLException {
        return new Candidato(rs.getInt("ID"), rs.getString("codigo"), rs.getDate("fecha"), rs.getInt("estado"), -1, rs.getInt("idUsuario"), rs.getInt("idEjercicio"), rs.getString("lenguajeOrigen"), rs.getString("lenguajeDestino"));
    }

    @Override
    protected Integer getKey(Candidato object) {
        return object.getId();
    }
    
    public List<Candidato> getCandidatosPropuestos(int idUsuario){
        return this.findByConditions(new QueryCondition[]{new QueryCondition("idUsuario", Operator.EQ, idUsuario)});
    }
    
}
