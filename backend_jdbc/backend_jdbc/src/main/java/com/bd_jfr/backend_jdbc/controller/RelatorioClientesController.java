package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.ClienteCompras;
import com.bd_jfr.backend_jdbc.service.RelatorioClientesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioClientesController {
    private final RelatorioClientesService relatorioClientesService;

    public RelatorioClientesController(RelatorioClientesService relatorioClientesService) {
        this.relatorioClientesService = relatorioClientesService;
    }

    @GetMapping("/clientes-mais-compraram")
    public List<ClienteCompras> findClientesQueMaisCompraram() {
        return relatorioClientesService.findClientesQueMaisCompraram();
    }
}
