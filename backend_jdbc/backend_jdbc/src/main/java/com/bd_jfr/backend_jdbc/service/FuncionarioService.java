package com.bd_jfr.backend_jdbc.service;

import com.bd_jfr.backend_jdbc.model.Funcionario;
import com.bd_jfr.backend_jdbc.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void createFuncionario(Funcionario funcionario) throws SQLException {
        funcionarioRepository.saveFuncionario(funcionario);
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public List<Funcionario> findByAtivo(boolean ativo) {
        return funcionarioRepository.findByAtivo(ativo);
    }

    public void updateFuncionario(String cpf, Funcionario funcionario) throws SQLException {
        funcionarioRepository.updateFuncionario(cpf, funcionario);
    }

    public void inativarFuncionario(String cpf) throws SQLException {
        funcionarioRepository.inativarFuncionario(cpf);
    }
}
