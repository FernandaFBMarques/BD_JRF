package com.bd_jfr.backend_jdbc.repository;

import com.bd_jfr.backend_jdbc.model.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProdutoRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProdutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Produto> findAll() {
        String sql = "SELECT * FROM Produtos";
        return jdbcTemplate.query(sql, new ProdutoRowMapper());
    }

    public void save(Produto produto) {
        String sql = "INSERT INTO Produtos (codigo_de_barras, nome_produto, modelo, cor, tamanho, categoria, preco, prazo, url_imagem) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, produto.getCodigoDeBarras(), produto.getNomeProduto(), produto.getModelo(), produto.getCor(), produto.getTamanho(), produto.getCategoria(), produto.getPreco(), produto.getPrazo(), produto.getUrlImagem());
    }

    public void update(String codigoDeBarras, Produto produto) {
        String sql = "UPDATE Produtos SET nome_produto = ?, modelo = ?, cor = ?, tamanho = ?, categoria = ?, preco = ?, prazo = ?, url_imagem = ? WHERE codigo_de_barras = ?";
        jdbcTemplate.update(sql, produto.getNomeProduto(), produto.getModelo(), produto.getCor(), produto.getTamanho(), produto.getCategoria(), produto.getPreco(), produto.getPrazo(), produto.getUrlImagem(), codigoDeBarras);
    }

    private static class ProdutoRowMapper implements RowMapper<Produto> {
        @Override
        public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Produto produto = new Produto();
            produto.setCodigoDeBarras(rs.getString("codigo_de_barras"));
            produto.setNomeProduto(rs.getString("nome_produto"));
            produto.setModelo(rs.getString("modelo"));
            produto.setCor(rs.getString("cor"));
            produto.setTamanho(rs.getInt("tamanho"));
            produto.setCategoria(rs.getString("categoria"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setPrazo(rs.getDate("prazo"));
            produto.setUrlImagem(rs.getString("url_imagem"));
            return produto;
        }
    }
}


