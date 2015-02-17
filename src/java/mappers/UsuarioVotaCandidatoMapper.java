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
import modelo.UsuarioVotaCandidato;

/**
 *
 * @author Johana
 */
public class UsuarioVotaCandidatoMapper extends AbstractMapper<UsuarioVotaCandidato, UsuarioVotaCandidato>{
    public UsuarioVotaCandidatoMapper(DataSource ds) {
        super(ds);
    }

    @Override
    protected String getTableName() {
        return "usuariovotacandidato";
    }

    @Override
    protected String[] getColumnNames() {
        return new String[] {"idUsuario", "idCandidato", "voto"};
    }

    @Override
    protected Object[] serializeObject(UsuarioVotaCandidato object) {
        return new Object[]{object.getIdUsuario(), object.getIdCandidato(), object.getVoto()};
    }

    @Override
    protected String[] getKeyColumnNames() {
        return new String[] {"idUsuario","idCandidato"};
    }

    @Override
    protected Object[] serializeKey(UsuarioVotaCandidato key) {
        return new Object[]{key.getIdUsuario(), key.getIdCandidato()};
    }

    @Override
    protected UsuarioVotaCandidato buildObject(ResultSet rs) throws SQLException {
        return new UsuarioVotaCandidato(rs.getInt("idUsuario"), rs.getInt("idCandidato"), rs.getInt("voto"));
    }
    
    @Override
    protected UsuarioVotaCandidato getKey(UsuarioVotaCandidato object) {
        return object;
    }
    
    public List<UsuarioVotaCandidato> getVotosDeUnUsuario(int idUsuario){
        return this.findByConditions(new QueryCondition[]{new QueryCondition("idUsuario", Operator.EQ, idUsuario)});
    }
    
    public List<UsuarioVotaCandidato> getVotosDeUnCandidato(int idCandidato){
        return this.findByConditions(new QueryCondition[]{new QueryCondition("idCandidato", Operator.EQ, idCandidato)});
    }
}
