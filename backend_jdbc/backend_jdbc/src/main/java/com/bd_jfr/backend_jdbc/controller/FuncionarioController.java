package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.Funcionario;
import com.bd_jfr.backend_jdbc.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public void createFuncionario(@RequestBody Funcionario funcionario) throws SQLException {
        funcionarioService.createFuncionario(funcionario);
    }

    @GetMapping
    public List<Funcionario> getAllFuncionarios(@RequestParam(required = false) Boolean ativo) {
        if (ativo != null) {
            return funcionarioService.findByAtivo(ativo);
        }
        return funcionarioService.findAll();
    }

    @PutMapping("/{cpf}")
    public void updateFuncionario(@PathVariable String cpf, @RequestBody Funcionario funcionario) throws SQLException {
        funcionarioService.updateFuncionario(cpf, funcionario);
    }

    @PutMapping("/inativar/{cpf}")
    public void inativarFuncionario(@PathVariable String cpf) throws SQLException {
        funcionarioService.inativarFuncionario(cpf);
    }
}