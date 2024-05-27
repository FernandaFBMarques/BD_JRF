package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.FazVendaProduto;
import com.bd_jfr.backend_jdbc.service.FazVendaProdutoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/fazVendaProduto")
public class FazVendaProdutoController {
    private final FazVendaProdutoService fazVendaProdutoService;

    @Autowired
    public FazVendaProdutoController(FazVendaProdutoService fazVendaProdutoService) {
        this.fazVendaProdutoService = fazVendaProdutoService;
    }

    @GetMapping
    public List<FazVendaProduto> findAll() {
        return fazVendaProdutoService.findAll();
    }

    @PostMapping
    public void create(@RequestBody FazVendaProduto fazVendaProduto) {
        fazVendaProdutoService.save(fazVendaProduto);
    }
}