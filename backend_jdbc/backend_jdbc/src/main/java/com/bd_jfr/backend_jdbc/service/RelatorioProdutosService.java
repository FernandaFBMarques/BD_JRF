package com.bd_jfr.backend_jdbc.service;

import com.bd_jfr.backend_jdbc.model.ProdutoVenda;
import com.bd_jfr.backend_jdbc.model.ProdutoVendaEstado;
import com.bd_jfr.backend_jdbc.repository.RelatorioProdutosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioProdutosService {
    private final RelatorioProdutosRepository relatorioProdutosRepository;

    public RelatorioProdutosService(RelatorioProdutosRepository relatorioProdutosRepository) {
        this.relatorioProdutosRepository = relatorioProdutosRepository;
    }

    public List<ProdutoVenda> findProdutosMaisVendidos() {
        return relatorioProdutosRepository.findProdutosMaisVendidos();
    }

    public List<ProdutoVendaEstado> findProdutosMaisVendidosPorEstado() {
        return relatorioProdutosRepository.findProdutosMaisVendidosPorEstado();
    }
}
