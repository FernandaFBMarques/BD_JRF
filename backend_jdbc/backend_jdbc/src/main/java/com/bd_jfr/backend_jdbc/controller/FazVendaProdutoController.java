package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.FazVendaProduto;
import com.bd_jfr.backend_jdbc.service.FazVendaProdutoService;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("/fazVendaProduto")
public class FazVendaProdutoController {
    private final FazVendaProdutoService fazVendaProdutoService;

    public FazVendaProdutoController(FazVendaProdutoService fazVendaProdutoService) {
        this.fazVendaProdutoService = fazVendaProdutoService;
    }

    @GetMapping
    public List<FazVendaProduto> findAll() {
        return fazVendaProdutoService.findAll();
    }

    @PostMapping
    public void create(@RequestBody FazVendaProduto fazVendaProduto) throws SQLException {
        fazVendaProdutoService.save(fazVendaProduto);
    }

    @PutMapping("/{fkVendaNumeroVenda}/{fkProdutosCodigoDeBarras}/quantidade")
    public void updateQuantidade(@PathVariable int fkVendaNumeroVenda,
                                 @PathVariable String fkProdutosCodigoDeBarras,
                                 @RequestBody FazVendaProduto fazVendaProduto) throws SQLException {
        fazVendaProdutoService.updateQuantidade(fkVendaNumeroVenda, fkProdutosCodigoDeBarras, fazVendaProduto.getQuantidadeDeProduto());
    }
}