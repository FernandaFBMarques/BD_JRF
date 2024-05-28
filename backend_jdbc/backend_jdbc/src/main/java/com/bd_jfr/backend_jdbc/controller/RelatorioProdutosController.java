package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.ProdutoVenda;
import com.bd_jfr.backend_jdbc.model.ProdutoVendaEstado;
import com.bd_jfr.backend_jdbc.service.RelatorioProdutosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioProdutosController {
    private final RelatorioProdutosService relatorioProdutosService;

    public RelatorioProdutosController(RelatorioProdutosService relatorioProdutosService) {
        this.relatorioProdutosService = relatorioProdutosService;
    }

    @GetMapping("/produtos-mais-vendidos")
    public List<ProdutoVenda> getProdutosMaisVendidos() {
        return relatorioProdutosService.findProdutosMaisVendidos();
    }

    @GetMapping("/produtos-mais-vendidos-por-estado")
    public List<ProdutoVendaEstado> getProdutosMaisVendidosPorEstado() {
        return relatorioProdutosService.findProdutosMaisVendidosPorEstado();
    }
}
