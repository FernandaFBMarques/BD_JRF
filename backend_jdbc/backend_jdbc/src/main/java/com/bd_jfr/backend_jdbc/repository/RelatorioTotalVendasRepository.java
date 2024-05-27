package com.bd_jfr.backend_jdbc.repository;


import com.bd_jfr.backend_jdbc.model.RelatorioTotalVendas;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Repository
public class RelatorioTotalVendasRepository {
    private final JdbcTemplate jdbcTemplate;

    public RelatorioTotalVendasRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RelatorioTotalVendas findRelatorioTotalVendas() {
        String sql = "SELECT " +
                "SUM(v.valor_venda) AS total_vendas, " +
                "SUM(nf.calculo_do_imposto) AS total_vendas_com_imposto " +
                "FROM Venda v " +
                "JOIN NF_de_comissionamento nf ON v.numero_venda = nf.fk_Venda_numero_venda";

        return jdbcTemplate.queryForObject(sql, relatorioTotalVendasRowMapper());
    }

    private RowMapper<RelatorioTotalVendas> relatorioTotalVendasRowMapper() {
        return new RowMapper<RelatorioTotalVendas>() {
            @Override
            public RelatorioTotalVendas mapRow(ResultSet rs, int rowNum) throws SQLException {
                RelatorioTotalVendas relatorio = new RelatorioTotalVendas();
                relatorio.setTotalVendas(round(rs.getDouble("total_vendas"), 2));
                relatorio.setTotalVendasComImposto(round(rs.getDouble("total_vendas_com_imposto"), 2));
                return relatorio;
            }
        };
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

