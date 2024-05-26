package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.Produto;
import com.bd_jfr.backend_jdbc.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoService.getAllProdutos();
    }

    @PostMapping("/addProduto")
    public void addProduto(@RequestBody Produto produto) {
        produtoService.addProduto(produto);
    }

    @PutMapping("/updateProduto/{codigoDeBarras}")
    public void updateProduto(@PathVariable String codigoDeBarras, @RequestBody Produto produto) {
        produtoService.updateProduto(codigoDeBarras, produto);
    }
}
