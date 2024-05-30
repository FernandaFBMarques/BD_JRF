package com.bd_jfr.backend_jdbc.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import com.bd_jfr.backend_jdbc.model.FazVendaProduto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FazVendaProdutoRepository {
    private final JdbcTemplate jdbcTemplate;

    public FazVendaProdutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FazVendaProduto> findAll() {
        String sql = "SELECT * FROM fazVendaProduto";
        return jdbcTemplate.query(sql, new FazVendaProdutoRowMapper());
    }

    public void save(FazVendaProduto fazVendaProduto) {
        String sql = "INSERT INTO fazVendaProduto (fk_Venda_numero_venda, fk_Produtos_codigo_de_barras, fk_atende_numero_atendimento, quantidade_de_produto) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, fazVendaProduto.getFkVendaNumeroVenda(), fazVendaProduto.getFkProdutosCodigoDeBarras(), fazVendaProduto.getFkAtendeNumeroAtendimento(), fazVendaProduto.getQuantidadeDeProduto());
    }

    public void updateQuantidade(int fkVendaNumeroVenda, String fkProdutosCodigoDeBarras, int quantidadeDeProduto) {
        String sql = "UPDATE fazVendaProduto SET quantidade_de_produto = ? WHERE fk_Venda_numero_venda = ? AND fk_Produtos_codigo_de_barras = ?";
        jdbcTemplate.update(sql, quantidadeDeProduto, fkVendaNumeroVenda, fkProdutosCodigoDeBarras);
    }

    private static class FazVendaProdutoRowMapper implements RowMapper<FazVendaProduto> {
        @Override
        public FazVendaProduto mapRow(ResultSet rs, int rowNum) throws SQLException {
            FazVendaProduto fazVendaProduto = new FazVendaProduto();
            fazVendaProduto.setFkVendaNumeroVenda(rs.getInt("fk_Venda_numero_venda"));
            fazVendaProduto.setFkProdutosCodigoDeBarras(rs.getString("fk_Produtos_codigo_de_barras"));
            fazVendaProduto.setFkAtendeNumeroAtendimento(rs.getInt("fk_atende_numero_atendimento"));
            fazVendaProduto.setQuantidadeDeProduto(rs.getInt("quantidade_de_produto"));
            return fazVendaProduto;
        }
    }
}