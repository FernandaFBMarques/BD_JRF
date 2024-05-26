package com.bd_jfr.backend_jdbc.repository;
import com.bd_jfr.backend_jdbc.model.Cliente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Cliente> findAll() {
        String sql = "SELECT c.*, (SELECT COUNT(*) FROM email e WHERE e.fk_Clientes_cnpj = c.cnpj) AS totalEmailsAlternativos FROM Clientes c";
        return jdbcTemplate.query(sql, new ClienteRowMapper());
    }

    private static class ClienteRowMapper implements RowMapper<Cliente> {
        @Override
        public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cliente cliente = new Cliente();
            cliente.setCnpj(rs.getString("cnpj"));
            cliente.setNomeLoja(rs.getString("nome_loja"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setEmail(rs.getString("email"));
            cliente.setRua(rs.getString("rua"));
            cliente.setNumero(rs.getInt("numero"));
            cliente.setCidade(rs.getString("cidade"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setEstado(rs.getString("estado"));
            cliente.setTipoVarejo(rs.getBoolean("tipo_varejo"));
            cliente.setTipoBoutique(rs.getBoolean("tipo_boutique"));
            cliente.setTotalEmailsAlternativos(rs.getInt("totalEmailsAlternativos"));
            return cliente;
        }
    }
}
