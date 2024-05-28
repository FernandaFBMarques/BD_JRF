package com.bd_jfr.backend_jdbc.repository;

import com.bd_jfr.backend_jdbc.model.ClienteCompras;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RelatorioClientesRepository {
    private final JdbcTemplate jdbcTemplate;

    public RelatorioClientesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ClienteCompras> findClientesQueMaisCompraram() {
        String sql = "SELECT c.nome_loja, SUM(v.valor_venda) AS total_compras " +
                "FROM Clientes c " +
                "JOIN atende a ON c.cnpj = a.fk_Clientes_cnpj " +
                "JOIN fazVendaProduto fvp ON a.numero_atendimento = fvp.fk_atende_numero_atendimento " +
                "JOIN Venda v ON fvp.fk_Venda_numero_venda = v.numero_venda " +
                "GROUP BY c.nome_loja " +
                "ORDER BY total_compras DESC";

        return jdbcTemplate.query(sql, new RowMapper<ClienteCompras>() {
            @Override
            public ClienteCompras mapRow(ResultSet rs, int rowNum) throws SQLException {
                ClienteCompras clienteCompras = new ClienteCompras();
                clienteCompras.setNomeLoja(rs.getString("nome_loja"));
                clienteCompras.setTotalCompras(rs.getDouble("total_compras"));
                return clienteCompras;
            }
        });
    }
}
