package com.bd_jfr.backend_jdbc.repository;

import com.bd_jfr.backend_jdbc.model.Funcionario;
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

    public List<Funcionario> findAll() {
        String sql = "SELECT f.*, " +
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

    public Optional<Funcionario> findByCpf(String cpf) {
        String sql = "SELECT * FROM Funcionarios WHERE cpf = ?";
        return jdbcTemplate.query(sql, new Object[]{cpf}, new FuncionarioRowMapper())
                .stream()
                .findFirst();
    }

    public int save(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionarios (cpf, nome, telefone, email, rua, numero, cidade, bairro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, funcionario.getCpf(), funcionario.getNome(), funcionario.getTelefone(), funcionario.getEmail(),
                funcionario.getRua(), funcionario.getNumero(), funcionario.getCidade(), funcionario.getBairro());
    }

    public int update(Funcionario funcionario) {
        String sql = "UPDATE Funcionarios SET nome = ?, telefone = ?, email = ?, rua = ?, numero = ?, cidade = ?, bairro = ? WHERE cpf = ?";
        return jdbcTemplate.update(sql, funcionario.getNome(), funcionario.getTelefone(), funcionario.getEmail(),
                funcionario.getRua(), funcionario.getNumero(), funcionario.getCidade(), funcionario.getBairro(), funcionario.getCpf());
    }

    public int deleteByCpf(String cpf) {
        String sql = "DELETE FROM Funcionarios WHERE cpf = ?";
        return jdbcTemplate.update(sql, cpf);
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
            funcionario.setCargo(rs.getString("cargo"));
            return funcionario;
        }
    }
}
