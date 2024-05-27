package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.RelatorioVenda;
import com.bd_jfr.backend_jdbc.service.RelatorioVendaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioVendaController {
    private final RelatorioVendaService relatorioVendaService;

    public RelatorioVendaController(RelatorioVendaService relatorioVendaService) {
        this.relatorioVendaService = relatorioVendaService;
    }

    @GetMapping("/vendas")
    public List<RelatorioVenda> findRelatorioVendas() {
        return relatorioVendaService.findRelatorioVendas();
    }
}
