package com.bd_jfr.backend_jdbc.repository;

import com.bd_jfr.backend_jdbc.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class FuncionarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Funcionario> rowMapper = (ResultSet rs, int rowNum) -> {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(rs.getString("cpf"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setTelefone(rs.getString("telefone"));
        funcionario.setEmail(rs.getString("email"));
        funcionario.setRua(rs.getString("rua"));
        funcionario.setNumero(rs.getInt("numero"));
        funcionario.setCidade(rs.getString("cidade"));
        funcionario.setBairro(rs.getString("bairro"));
        return funcionario;
    };

    public List<Funcionario> findAll() {
        String sql = "SELECT * FROM Funcionarios";
        return jdbcTemplate.query(sql, rowMapper);
    }
}