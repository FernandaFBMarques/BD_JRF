package com.bd_jfr.backend_jdbc.repository;

import com.bd_jfr.backend_jdbc.model.Atende;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AtendeRepository {
    private final JdbcTemplate jdbcTemplate;

    public AtendeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Atende> findAll() {
        String sql = "SELECT * FROM atende ORDER BY numero_atendimento DESC";
        return jdbcTemplate.query(sql, atendeRowMapper());
    }

    public void save(Atende atende) {
        String sql = "INSERT INTO atende (fk_Equipe_de_vendas_fk_Funcionarios_cpf, fk_Clientes_cnpj) VALUES (?, ?)";
        jdbcTemplate.update(sql, atende.getFkEquipeDeVendasCpf(), atende.getFkClientesCnpj());
    }

    private RowMapper<Atende> atendeRowMapper() {
        return new RowMapper<Atende>() {
            @Override
            public Atende mapRow(ResultSet rs, int rowNum) throws SQLException {
                Atende atende = new Atende();
                atende.setNumeroAtendimento(rs.getInt("numero_atendimento"));
                atende.setFkEquipeDeVendasCpf(rs.getString("fk_Equipe_de_vendas_fk_Funcionarios_cpf"));
                atende.setFkClientesCnpj(rs.getString("fk_Clientes_cnpj"));
                return atende;
            }
        };
    }
}
