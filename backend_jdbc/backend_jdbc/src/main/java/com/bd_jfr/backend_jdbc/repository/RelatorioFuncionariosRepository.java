package com.bd_jfr.backend_jdbc.repository;

import com.bd_jfr.backend_jdbc.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RelatorioFuncionariosRepository {
    private final JdbcTemplate jdbcTemplate;

    public RelatorioFuncionariosRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FuncionarioDependente> findAnaliseComDependentes() {
        String sql = "SELECT f.nome, d.nome AS dependente FROM Funcionarios f " +
                "LEFT JOIN Dependente d ON f.cpf = d.fk_Contadores_fk_Funcionarios_cpf";
        return jdbcTemplate.query(sql, new RowMapper<FuncionarioDependente>() {
            @Override
            public FuncionarioDependente mapRow(ResultSet rs, int rowNum) throws SQLException {
                FuncionarioDependente fd = new FuncionarioDependente();
                fd.setNomeFuncionario(rs.getString("nome"));
                fd.setNomeDependente(rs.getString("dependente"));
                return fd;
            }
        });
    }

    public List<FuncionarioRelacionamento> findAnaliseAutoRelacionamento() {
        String sql = "SELECT f.nome AS nomeFuncionario, g.nome AS nomeGerente " +
                "FROM Funcionarios f " +
                "LEFT JOIN Equipe_de_vendas ev ON f.cpf = ev.fk_Funcionarios_cpf " +
                "LEFT JOIN Funcionarios g ON ev.mat_gerente = g.cpf";
        return jdbcTemplate.query(sql, new RowMapper<FuncionarioRelacionamento>() {
            @Override
            public FuncionarioRelacionamento mapRow(ResultSet rs, int rowNum) throws SQLException {
                FuncionarioRelacionamento fr = new FuncionarioRelacionamento();
                fr.setNomeFuncionario(rs.getString("nomeFuncionario"));
                fr.setNomeGerente(rs.getString("nomeGerente"));
                return fr;
            }
        });
    }

    public FuncionarioVenda findVendedorQueMaisVendeu() {
        String sql = "SELECT f.nome AS nomeVendedor, SUM(v.valor_venda) AS totalVendas FROM Funcionarios f " +
                "JOIN Equipe_de_vendas ev ON f.cpf = ev.fk_Funcionarios_cpf " +
                "JOIN atende a ON ev.fk_Funcionarios_cpf = a.fk_Equipe_de_vendas_fk_Funcionarios_cpf " +
                "JOIN fazVendaProduto fvp ON a.numero_atendimento = fvp.fk_atende_numero_atendimento " +
                "JOIN Venda v ON fvp.fk_Venda_numero_venda = v.numero_venda " +
                "GROUP BY f.nome ORDER BY totalVendas DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new RowMapper<FuncionarioVenda>() {
            @Override
            public FuncionarioVenda mapRow(ResultSet rs, int rowNum) throws SQLException {
                FuncionarioVenda fv = new FuncionarioVenda();
                fv.setNomeVendedor(rs.getString("nomeVendedor"));
                fv.setTotalVendas(rs.getDouble("totalVendas"));
                return fv;
            }
        });
    }

    public FuncionarioNota findContadorQueMaisGerouNotaFiscal() {
        String sql = "SELECT f.nome AS nomeContador, COUNT(nf.numero) AS totalNotas FROM Funcionarios f " +
                "JOIN NF_de_comissionamento nf ON f.cpf = nf.fk_Contadores_fk_Funcionarios_cpf " +
                "GROUP BY f.nome ORDER BY totalNotas DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new RowMapper<FuncionarioNota>() {
            @Override
            public FuncionarioNota mapRow(ResultSet rs, int rowNum) throws SQLException {
                FuncionarioNota fn = new FuncionarioNota();
                fn.setNomeContador(rs.getString("nomeContador"));
                fn.setTotalNotas(rs.getInt("totalNotas"));
                return fn;
            }
        });
    }

    public FuncionarioAtendimento findVendedorQueMaisFezAtendimento() {
        String sql = "SELECT f.nome AS nomeVendedor, COUNT(a.numero_atendimento) AS totalAtendimentos FROM Funcionarios f " +
                "JOIN Equipe_de_vendas ev ON f.cpf = ev.fk_Funcionarios_cpf " +
                "JOIN atende a ON ev.fk_Funcionarios_cpf = a.fk_Equipe_de_vendas_fk_Funcionarios_cpf " +
                "GROUP BY f.nome ORDER BY totalAtendimentos DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new RowMapper<FuncionarioAtendimento>() {
            @Override
            public FuncionarioAtendimento mapRow(ResultSet rs, int rowNum) throws SQLException {
                FuncionarioAtendimento fa = new FuncionarioAtendimento();
                fa.setNomeVendedor(rs.getString("nomeVendedor"));
                fa.setTotalAtendimentos(rs.getInt("totalAtendimentos"));
                return fa;
            }
        });
    }
}
