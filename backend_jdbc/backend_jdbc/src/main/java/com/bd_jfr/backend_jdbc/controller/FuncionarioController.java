package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.Funcionario;
import com.bd_jfr.backend_jdbc.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioService.getAllFuncionarios();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Funcionario> getFuncionarioByCpf(@PathVariable String cpf) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioByCpf(cpf);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Funcionario> createFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioService.createFuncionario(funcionario);
        return ResponseEntity.ok(funcionario);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable String cpf, @RequestBody Funcionario funcionarioDetails) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioByCpf(cpf);
        if (funcionario.isPresent()) {
            funcionarioDetails.setCpf(cpf);
            funcionarioService.updateFuncionario(funcionarioDetails);
            return ResponseEntity.ok(funcionarioDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable String cpf) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioByCpf(cpf);
        if (funcionario.isPresent()) {
            funcionarioService.deleteFuncionario(cpf);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
