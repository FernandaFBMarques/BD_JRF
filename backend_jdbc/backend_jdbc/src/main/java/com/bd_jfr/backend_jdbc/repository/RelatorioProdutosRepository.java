package com.bd_jfr.backend_jdbc.repository;

import com.bd_jfr.backend_jdbc.model.ProdutoVenda;
import com.bd_jfr.backend_jdbc.model.ProdutoVendaEstado;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RelatorioProdutosRepository {
    private final JdbcTemplate jdbcTemplate;

    public RelatorioProdutosRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProdutoVenda> findProdutosMaisVendidos() {
        String sql = "SELECT p.nome_produto, SUM(fvp.quantidade_de_produto) AS totalVendas " +
                "FROM Produtos p " +
                "JOIN fazVendaProduto fvp ON p.codigo_de_barras = fvp.fk_Produtos_codigo_de_barras " +
                "GROUP BY p.nome_produto " +
                "ORDER BY totalVendas DESC";
        return jdbcTemplate.query(sql, new RowMapper<ProdutoVenda>() {
            @Override
            public ProdutoVenda mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProdutoVenda pv = new ProdutoVenda();
                pv.setNomeProduto(rs.getString("nome_produto"));
                pv.setTotalVendas(rs.getInt("totalVendas"));
                return pv;
            }
        });
    }

    public List<ProdutoVendaEstado> findProdutosMaisVendidosPorEstado() {
        String sql = "SELECT p.nome_produto, c.estado, SUM(fvp.quantidade_de_produto) AS totalVendas " +
                "FROM Produtos p " +
                "JOIN fazVendaProduto fvp ON p.codigo_de_barras = fvp.fk_Produtos_codigo_de_barras " +
                "JOIN atende a ON fvp.fk_atende_numero_atendimento = a.numero_atendimento " +
                "JOIN Clientes c ON a.fk_Clientes_cnpj = c.cnpj " +
                "GROUP BY p.nome_produto, c.estado " +
                "ORDER BY c.estado, totalVendas DESC";
        return jdbcTemplate.query(sql, new RowMapper<ProdutoVendaEstado>() {
            @Override
            public ProdutoVendaEstado mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProdutoVendaEstado pve = new ProdutoVendaEstado();
                pve.setNomeProduto(rs.getString("nome_produto"));
                pve.setEstado(rs.getString("estado"));
                pve.setTotalVendas(rs.getInt("totalVendas"));
                return pve;
            }
        });
    }
}
