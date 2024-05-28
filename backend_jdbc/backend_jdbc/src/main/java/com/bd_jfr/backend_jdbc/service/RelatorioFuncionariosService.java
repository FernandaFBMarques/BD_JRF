package com.bd_jfr.backend_jdbc.service;

import com.bd_jfr.backend_jdbc.model.*;
import com.bd_jfr.backend_jdbc.repository.RelatorioFuncionariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioFuncionariosService {
    private final RelatorioFuncionariosRepository relatorioFuncionariosRepository;

    public RelatorioFuncionariosService(RelatorioFuncionariosRepository relatorioFuncionariosRepository) {
        this.relatorioFuncionariosRepository = relatorioFuncionariosRepository;
    }

    public List<FuncionarioDependente> findAnaliseComDependentes() {
        return relatorioFuncionariosRepository.findAnaliseComDependentes();
    }

    public List<FuncionarioRelacionamento> findAnaliseAutoRelacionamento() {
        return relatorioFuncionariosRepository.findAnaliseAutoRelacionamento();
    }

    public FuncionarioVenda findVendedorQueMaisVendeu() {
        return relatorioFuncionariosRepository.findVendedorQueMaisVendeu();
    }

    public FuncionarioNota findContadorQueMaisGerouNotaFiscal() {
        return relatorioFuncionariosRepository.findContadorQueMaisGerouNotaFiscal();
    }

    public FuncionarioAtendimento findVendedorQueMaisFezAtendimento() {
        return relatorioFuncionariosRepository.findVendedorQueMaisFezAtendimento();
    }
}
