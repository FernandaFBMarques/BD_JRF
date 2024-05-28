package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.RelatorioVendasEquipe;
import com.bd_jfr.backend_jdbc.service.RelatorioVendasEquipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioVendasEquipeController {
    private final RelatorioVendasEquipeService relatorioVendasEquipeService;

    public RelatorioVendasEquipeController(RelatorioVendasEquipeService relatorioVendasEquipeService) {
        this.relatorioVendasEquipeService = relatorioVendasEquipeService;
    }

    @GetMapping("/vendas-equipe")
    public List<RelatorioVendasEquipe> findRelatorioVendasEquipe() {
        return relatorioVendasEquipeService.findRelatorioVendasEquipe();
    }

    @GetMapping("/vendas-por-membro")
    public List<RelatorioVendasEquipe> getVendasPorMembro() {
        return relatorioVendasEquipeService.getVendasPorMembro();
    }

}
