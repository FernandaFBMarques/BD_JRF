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

    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> getFuncionarioByCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf);
    }


    public void createFuncionario(Funcionario funcionario) throws SQLException {
        funcionarioRepository.saveFuncionario(funcionario);
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

//    public void updateFuncionario(Funcionario funcionario) {
//        funcionarioRepository.update(funcionario);
//    }
//
//    public void deleteFuncionario(String cpf) {
//        funcionarioRepository.deleteByCpf(cpf);
//    }
}
