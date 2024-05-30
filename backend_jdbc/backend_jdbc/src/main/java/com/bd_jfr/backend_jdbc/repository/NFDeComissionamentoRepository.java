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
        String sql = "SELECT * FROM NF_de_comissionamento";
        return jdbcTemplate.query(sql, new RowMapper<NFDeComissionamento>() {
            @Override
            public NFDeComissionamento mapRow(ResultSet rs, int rowNum) throws SQLException {
                NFDeComissionamento nf = new NFDeComissionamento();
                nf.setNumero(rs.getInt("numero"));
                nf.setFkContadoresFkFuncionariosCpf(rs.getString("fk_Contadores_fk_Funcionarios_cpf"));
                nf.setFkVendaNumeroVenda(rs.getInt("fk_Venda_numero_venda"));
                nf.setFkEquipeDeVendasFkFuncionariosCpf(rs.getString("fk_Equipe_de_vendas_fk_Funcionarios_cpf"));
                nf.setSerie(rs.getInt("serie"));
                nf.setDataEmissao(rs.getDate("data_emissao"));
                nf.setDataSaida(rs.getDate("data_saida"));
                nf.setCalculoDoImposto(rs.getDouble("calculo_do_imposto"));
                return nf;
            }
        });
    }

    public void save(NFDeComissionamento nf) {
        String sql = "INSERT INTO NF_de_comissionamento (fk_Contadores_fk_Funcionarios_cpf, fk_Venda_numero_venda) VALUES (?, ?)";
        jdbcTemplate.update(sql, nf.getFkContadoresFkFuncionariosCpf(), nf.getFkVendaNumeroVenda());
    }
}
