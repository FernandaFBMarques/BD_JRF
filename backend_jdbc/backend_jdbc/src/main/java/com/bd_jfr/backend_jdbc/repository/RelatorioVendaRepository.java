package com.bd_jfr.backend_jdbc.repository;

import com.bd_jfr.backend_jdbc.model.RelatorioVenda;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RelatorioVendaRepository {
    private final JdbcTemplate jdbcTemplate;

    public RelatorioVendaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RelatorioVenda> findRelatorioVendas() {
        String sql = "SELECT v.numero_venda, v.valor_venda AS total_venda, nf.numero AS numero_nf, " +
                "f.nome AS vendedor, c.nome_loja AS cliente, nf.calculo_do_imposto " +
                "FROM Venda v " +
                "JOIN fazVendaProduto fvp ON v.numero_venda = fvp.fk_Venda_numero_venda " +
                "JOIN atende a ON fvp.fk_atende_numero_atendimento = a.numero_atendimento " +
                "JOIN NF_de_comissionamento nf ON v.numero_venda = nf.fk_Venda_numero_venda " +
                "JOIN Funcionarios f ON a.fk_Equipe_de_vendas_fk_Funcionarios_cpf = f.cpf " +
                "JOIN Clientes c ON a.fk_Clientes_cnpj = c.cnpj " +
                "ORDER BY v.numero_venda DESC";

        return jdbcTemplate.query(sql, relatorioVendaRowMapper());
    }

    private RowMapper<RelatorioVenda> relatorioVendaRowMapper() {
        return new RowMapper<RelatorioVenda>() {
            @Override
            public RelatorioVenda mapRow(ResultSet rs, int rowNum) throws SQLException {
                RelatorioVenda relatorio = new RelatorioVenda();
                relatorio.setNumeroVenda(rs.getInt("numero_venda"));
                relatorio.setTotalVenda(rs.getDouble("total_venda"));
                relatorio.setNumeroNf(rs.getInt("numero_nf"));
                relatorio.setVendedor(rs.getString("vendedor"));
                relatorio.setCliente(rs.getString("cliente"));
                relatorio.setCalculoDoImposto(rs.getDouble("calculo_do_imposto"));
                return relatorio;
            }
        };
    }
}
