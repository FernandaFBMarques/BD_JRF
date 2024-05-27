package com.bd_jfr.backend_jdbc.repository;

import com.bd_jfr.backend_jdbc.model.RelatorioVendasEquipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Repository
public class RelatorioVendasEquipeRepository {
    private final JdbcTemplate jdbcTemplate;

    public RelatorioVendasEquipeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RelatorioVendasEquipe> findRelatorioVendasEquipe() {
        String sql = "SELECT f.nome AS nome_vendedor, " +
                "SUM(v.valor_venda) AS total_vendas, " +
                "SUM(nf.calculo_do_imposto) AS total_impostos, " +
                "(SELECT g.nome FROM Funcionarios g WHERE g.cpf = ev.mat_gerente) AS nome_gerente " +
                "FROM Venda v " +
                "JOIN fazVendaProduto fvp ON v.numero_venda = fvp.fk_Venda_numero_venda " +
                "JOIN atende a ON fvp.fk_atende_numero_atendimento = a.numero_atendimento " +
                "JOIN NF_de_comissionamento nf ON v.numero_venda = nf.fk_Venda_numero_venda " +
                "JOIN Equipe_de_vendas ev ON a.fk_Equipe_de_vendas_fk_Funcionarios_cpf = ev.fk_Funcionarios_cpf " +
                "JOIN Funcionarios f ON ev.fk_Funcionarios_cpf = f.cpf " +
                "GROUP BY f.nome, ev.mat_gerente " +
                "ORDER BY f.nome";

        return jdbcTemplate.query(sql, relatorioVendasEquipeRowMapper());
    }

    private RowMapper<RelatorioVendasEquipe> relatorioVendasEquipeRowMapper() {
        return new RowMapper<RelatorioVendasEquipe>() {
            @Override
            public RelatorioVendasEquipe mapRow(ResultSet rs, int rowNum) throws SQLException {
                RelatorioVendasEquipe relatorio = new RelatorioVendasEquipe();
                relatorio.setNomeVendedor(rs.getString("nome_vendedor"));
                relatorio.setTotalVendas(round(rs.getDouble("total_vendas"), 2));
                relatorio.setTotalImpostos(round(rs.getDouble("total_impostos"), 2));
                relatorio.setNomeGerente(rs.getString("nome_gerente"));
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
