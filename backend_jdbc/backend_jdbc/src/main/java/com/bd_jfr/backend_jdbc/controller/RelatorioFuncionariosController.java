package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.*;
import com.bd_jfr.backend_jdbc.service.RelatorioFuncionariosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioFuncionariosController {
    private final RelatorioFuncionariosService relatorioFuncionariosService;

    public RelatorioFuncionariosController(RelatorioFuncionariosService relatorioFuncionariosService) {
        this.relatorioFuncionariosService = relatorioFuncionariosService;
    }

    @GetMapping("/analise-dependentes")
    public List<FuncionarioDependente> getAnaliseComDependentes() {
        return relatorioFuncionariosService.findAnaliseComDependentes();
    }

    @GetMapping("/analise-auto-relacionamento")
    public List<FuncionarioRelacionamento> getAnaliseAutoRelacionamento() {
        return relatorioFuncionariosService.findAnaliseAutoRelacionamento();
    }

    @GetMapping("/vendedor-mais-vendeu")
    public FuncionarioVenda getVendedorQueMaisVendeu() {
        return relatorioFuncionariosService.findVendedorQueMaisVendeu();
    }

    @GetMapping("/contador-mais-notas")
    public FuncionarioNota getContadorQueMaisGerouNotaFiscal() {
        return relatorioFuncionariosService.findContadorQueMaisGerouNotaFiscal();
    }

    @GetMapping("/vendedor-mais-atendimento")
    public FuncionarioAtendimento getVendedorQueMaisFezAtendimento() {
        return relatorioFuncionariosService.findVendedorQueMaisFezAtendimento();
    }
}
