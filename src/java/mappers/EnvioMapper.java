/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import modelo.Envio;

/**
 *
 * @author Julián
 */
public class EnvioMapper extends AbstractMapper <Envio, Integer>{

    public EnvioMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "envio";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"ID", "codigo", "fecha", "puntuacion", "idUsuario", "idEjercicio", "lenguajeOrigen", "lenguajeDestino"};
    }

    @Override
    protected Object[] serializeObject(Envio object) {
        return new Object[]{object.getId(), object.getCodigo(), object.getFecha(), object.getPuntuacion(), object.getIdUsuario(), object.getIdEjercicio(), object.getLenguajeOrigen(), object.getLenguajeDestino()};
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
    protected Envio buildObject(ResultSet rs) throws SQLException {
        return new Envio(rs.getInt("ID"), rs.getString("codigo"), rs.getDate("fecha"), rs.getInt("puntuacion"), rs.getInt("idUsuario"), rs.getInt("idEjercicio"), rs.getString("lenguajeOrigen"), rs.getString("lenguajeDestino"));
    }

    @Override
    protected Integer getKey(Envio object) {
        return object.getId();
    }
}
