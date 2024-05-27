package com.bd_jfr.backend_jdbc.repository;

import com.bd_jfr.backend_jdbc.model.Funcionario;
import com.bd_jfr.backend_jdbc.model.Contador;
import com.bd_jfr.backend_jdbc.model.EquipeDeVendas;
import com.bd_jfr.backend_jdbc.model.Dependente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class FuncionarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Optional<Funcionario> findByCpf(String cpf) {
        String sql = "SELECT * FROM Funcionarios WHERE cpf = ?";
        return jdbcTemplate.query(sql, new Object[]{cpf}, new FuncionarioRowMapper())
                .stream()
                .findFirst();
    }

    public List<Funcionario> findAll() {
        String sql = "SELECT f.*, " +
                "c.clt, " +
                "e.cnpj, e.mat_gerente, " +
                "CASE " +
                "  WHEN c.fk_Funcionarios_cpf IS NOT NULL THEN 'Contador' " +
                "  WHEN e.fk_Funcionarios_cpf IS NOT NULL THEN 'Equipe de Vendas' " +
                "  ELSE 'Outro' " +
                "END as cargo " +
                "FROM Funcionarios f " +
                "LEFT JOIN Contadores c ON f.cpf = c.fk_Funcionarios_cpf " +
                "LEFT JOIN Equipe_de_vendas e ON f.cpf = e.fk_Funcionarios_cpf";
        return jdbcTemplate.query(sql, new FuncionarioRowMapper());
    }

    public void saveFuncionario(Funcionario funcionario) throws SQLException {
        String sqlFuncionario = "INSERT INTO Funcionarios (cpf, nome, telefone, email, rua, numero, cidade, bairro, esta_ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlFuncionario,
                funcionario.getCpf(),
                funcionario.getNome(),
                funcionario.getTelefone(),
                funcionario.getEmail(),
                funcionario.getRua(),
                funcionario.getNumero(),
                funcionario.getCidade(),
                funcionario.getBairro(),
                funcionario.isEstaAtivo());

        if (funcionario.getContador() != null) {
            saveContador(funcionario.getContador());
        } else if (funcionario.getEquipeDeVendas() != null) {
            saveEquipeDeVendas(funcionario.getEquipeDeVendas());
        }
    }

    public void saveContador(Contador contador) throws SQLException {
        String sqlContador = "INSERT INTO Contadores (fk_Funcionarios_cpf, clt) VALUES (?, ?)";
        jdbcTemplate.update(sqlContador,
                contador.getFkFuncionariosCpf(),
                contador.getClt());

        for (Dependente dependente : contador.getDependentes()) {
            saveDependente(dependente);
        }
    }

    public void saveDependente(Dependente dependente) throws SQLException {
        String sqlDependente = "INSERT INTO Dependente (cpf, fk_Contadores_fk_Funcionarios_cpf, nome) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlDependente,
                dependente.getCpf(),
                dependente.getFkContadoresFkFuncionariosCpf(),
                dependente.getNome());
    }

    public void saveEquipeDeVendas(EquipeDeVendas equipeDeVendas) throws SQLException {
        String sqlEquipeDeVendas = "INSERT INTO Equipe_de_vendas (fk_Funcionarios_cpf, cnpj, mat_gerente) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlEquipeDeVendas,
                equipeDeVendas.getFkFuncionariosCpf(),
                equipeDeVendas.getCnpj(),
                equipeDeVendas.getMatGerente());
    }

    private static class FuncionarioRowMapper implements RowMapper<Funcionario> {
        @Override
        public Funcionario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Funcionario funcionario = new Funcionario();
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setTelefone(rs.getString("telefone"));
            funcionario.setEmail(rs.getString("email"));
            funcionario.setRua(rs.getString("rua"));
            funcionario.setNumero(rs.getInt("numero"));
            funcionario.setCidade(rs.getString("cidade"));
            funcionario.setBairro(rs.getString("bairro"));
            funcionario.setEstaAtivo(rs.getBoolean("esta_ativo"));

            String cargo = rs.getString("cargo");
            if ("Contador".equals(cargo)) {
                Contador contador = new Contador();
                contador.setFkFuncionariosCpf(rs.getString("cpf"));
                contador.setClt(rs.getString("clt"));
                funcionario.setContador(contador);
            } else if ("Equipe de Vendas".equals(cargo)) {
                EquipeDeVendas equipeDeVendas = new EquipeDeVendas();
                equipeDeVendas.setFkFuncionariosCpf(rs.getString("cpf"));
                equipeDeVendas.setCnpj(rs.getString("cnpj"));
                equipeDeVendas.setMatGerente(rs.getString("mat_gerente"));
                funcionario.setEquipeDeVendas(equipeDeVendas);
            }

            return funcionario;
        }
    }
}
