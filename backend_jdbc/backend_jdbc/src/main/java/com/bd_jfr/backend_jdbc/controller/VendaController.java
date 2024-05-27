package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.Venda;
import com.bd_jfr.backend_jdbc.service.VendaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    private final VendaService vendaService;

    @Autowired
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping
    public List<Venda> findAll() {
        return vendaService.findAll();
    }

    @PostMapping
    public void create(@RequestBody Venda venda) {
        vendaService.save(venda);
    }
}
