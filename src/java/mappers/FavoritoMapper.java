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
import modelo.Favorito;

/**
 *
 * @author Julián
 */
public class FavoritoMapper extends AbstractMapper <Favorito, Favorito>{

    public FavoritoMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "favorito";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"idUsuario", "idEjercicio", "lenguajeOrigen", "lenguajeDestino"};
    }

    @Override
    protected Object[] serializeObject(Favorito object) {
        return new Object[]{object.getIdUsuario(), object.getEjercicio(), object.getLenguajeOrigen(), object.getLenguajeDestino()};
    }

    @Override
    protected String[] getKeyColumnNames() {
        return new String[] {"idUsuario", "idEjercicio", "lenguajeOrigen", "lenguajeDestino"};
    }

    @Override
    protected Object[] serializeKey(Favorito key) {
        return new Object[]{key.getIdUsuario(), key.getEjercicio(), key.getLenguajeOrigen(), key.getLenguajeDestino()};
    }

    @Override
    protected Favorito buildObject(ResultSet rs) throws SQLException {
        return new Favorito(rs.getInt("idUsuario"), rs.getInt("idEjercicio"), rs.getString("lenguajeOrigen"), rs.getString("lenguajeDestino"));
    }

    @Override
    protected Favorito getKey(Favorito object) {
        return object;
    }
    
    public List<Favorito> getEFavoritosDeUnUsuario(int idUsuario){
        return this.findByConditions(new QueryCondition[]{new QueryCondition("idUsuario", Operator.EQ, idUsuario)});
    }
}
