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

    public void save(Cliente cliente, List<String> emails) {
        String sqlCliente = "INSERT INTO Clientes (cnpj, nome_loja, telefone, email, rua, numero, cidade, bairro, estado, tipo_varejo, tipo_boutique) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlCliente, cliente.getCnpj(), cliente.getNomeLoja(), cliente.getTelefone(), cliente.getEmail(), cliente.getRua(), cliente.getNumero(), cliente.getCidade(), cliente.getBairro(), cliente.getEstado(), cliente.isTipoVarejo(), cliente.isTipoBoutique());

        String sqlEmail = "INSERT INTO email (fk_Clientes_cnpj, email) VALUES (?, ?)";
        for (String email : emails) {
            jdbcTemplate.update(sqlEmail, cliente.getCnpj(), email);
        }
    }

    public void update(Cliente cliente, List<String> emails) {
        String sqlCliente = "UPDATE Clientes SET nome_loja = ?, telefone = ?, email = ?, rua = ?, numero = ?, cidade = ?, bairro = ?, estado = ?, tipo_varejo = ?, tipo_boutique = ? WHERE cnpj = ?";
        jdbcTemplate.update(sqlCliente, cliente.getNomeLoja(), cliente.getTelefone(), cliente.getEmail(), cliente.getRua(), cliente.getNumero(), cliente.getCidade(), cliente.getBairro(), cliente.getEstado(), cliente.isTipoVarejo(), cliente.isTipoBoutique(), cliente.getCnpj());

        String sqlDeleteEmails = "DELETE FROM email WHERE fk_Clientes_cnpj = ?";
        jdbcTemplate.update(sqlDeleteEmails, cliente.getCnpj());

        String sqlEmail = "INSERT INTO email (fk_Clientes_cnpj, email) VALUES (?, ?)";
        for (String email : emails) {
            jdbcTemplate.update(sqlEmail, cliente.getCnpj(), email);
        }
    }

    public void delete(String cnpj) {
        String sqlDeleteEmails = "DELETE FROM email WHERE fk_Clientes_cnpj = ?";
        jdbcTemplate.update(sqlDeleteEmails, cnpj);

        String sqlCliente = "DELETE FROM Clientes WHERE cnpj = ?";
        jdbcTemplate.update(sqlCliente, cnpj);
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
