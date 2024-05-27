package com.bd_jfr.backend_jdbc.repository;

import com.bd_jfr.backend_jdbc.model.Venda;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VendaRepository {
    private final JdbcTemplate jdbcTemplate;

    public VendaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Venda> findAll() {
        String sql = "SELECT * FROM Venda ORDER BY numero_venda DESC";
        return jdbcTemplate.query(sql, vendaRowMapper());
    }

    public void save(Venda venda) {
        String sql = "INSERT INTO Venda (data, valor_venda) VALUES (?, ?)";
        jdbcTemplate.update(sql, venda.getData(), venda.getValorVenda());
    }

    private RowMapper<Venda> vendaRowMapper() {
        return new RowMapper<Venda>() {
            @Override
            public Venda mapRow(ResultSet rs, int rowNum) throws SQLException {
                Venda venda = new Venda();
                venda.setNumeroVenda(rs.getInt("numero_venda"));
                venda.setData(rs.getDate("data"));
                venda.setValorVenda(rs.getDouble("valor_venda"));
                return venda;
            }
        };
    }
}