package com.bd_jfr.backend_jdbc.repository;

import com.bd_jfr.backend_jdbc.model.NFDeComissionamento;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NFDeComissionamentoRepository {
    private final JdbcTemplate jdbcTemplate;

    public NFDeComissionamentoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<NFDeComissionamento> findAll() {
        String sql = "SELECT * FROM NF_de_comissionamento ORDER BY numero DESC";
        return jdbcTemplate.query(sql, nfDeComissionamentoRowMapper());
    }

    public void save(NFDeComissionamento nfDeComissionamento) {
        String sql = "INSERT INTO NF_de_comissionamento (fk_Contadores_fk_Funcionarios_cpf, fk_Venda_numero_venda, fk_Equipe_de_vendas_fk_Funcionarios_cpf, serie, data_emissao, data_saida, calculo_do_imposto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, nfDeComissionamento.getFkContadoresCpf(), nfDeComissionamento.getFkVendaNumeroVenda(), nfDeComissionamento.getFkEquipeDeVendasCpf(), nfDeComissionamento.getSerie(), nfDeComissionamento.getDataEmissao(), nfDeComissionamento.getDataSaida(), nfDeComissionamento.getCalculoDoImposto());
    }

    private RowMapper<NFDeComissionamento> nfDeComissionamentoRowMapper() {
        return new RowMapper<NFDeComissionamento>() {
            @Override
            public NFDeComissionamento mapRow(ResultSet rs, int rowNum) throws SQLException {
                NFDeComissionamento nfDeComissionamento = new NFDeComissionamento();
                nfDeComissionamento.setNumero(rs.getInt("numero"));
                nfDeComissionamento.setFkContadoresCpf(rs.getString("fk_Contadores_fk_Funcionarios_cpf"));
                nfDeComissionamento.setFkVendaNumeroVenda(rs.getInt("fk_Venda_numero_venda"));
                nfDeComissionamento.setFkEquipeDeVendasCpf(rs.getString("fk_Equipe_de_vendas_fk_Funcionarios_cpf"));
                nfDeComissionamento.setSerie(rs.getInt("serie"));
                nfDeComissionamento.setDataEmissao(rs.getDate("data_emissao"));
                nfDeComissionamento.setDataSaida(rs.getDate("data_saida"));
                nfDeComissionamento.setCalculoDoImposto(rs.getDouble("calculo_do_imposto"));
                return nfDeComissionamento;
            }
        };
    }
}
