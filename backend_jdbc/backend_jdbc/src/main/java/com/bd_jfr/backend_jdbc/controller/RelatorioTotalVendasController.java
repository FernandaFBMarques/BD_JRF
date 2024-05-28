package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.RelatorioTotalVendas;
import com.bd_jfr.backend_jdbc.service.RelatorioTotalVendasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatorioTotalVendasController {
    private final RelatorioTotalVendasService relatorioTotalVendasService;

    public RelatorioTotalVendasController(RelatorioTotalVendasService relatorioTotalVendasService) {
        this.relatorioTotalVendasService = relatorioTotalVendasService;
    }

    @GetMapping("/total-vendas")
    public RelatorioTotalVendas findRelatorioTotalVendas() {
        return relatorioTotalVendasService.findRelatorioTotalVendas();
    }

    @GetMapping("/total-vendas-sem-imposto")
    public double getTotalVendasSemImposto() {
        return relatorioTotalVendasService.getTotalVendasSemImposto();
    }

    @GetMapping("/total-vendas-com-imposto")
    public double getTotalVendasComImposto() {
        return relatorioTotalVendasService.getTotalVendasComImposto();
    }


}
