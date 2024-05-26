package com.bd_jfr.backend_jdbc.service;

import com.bd_jfr.backend_jdbc.model.Produto;
import com.bd_jfr.backend_jdbc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public void addProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public void updateProduto(String codigoDeBarras, Produto produto) {
        produtoRepository.update(codigoDeBarras, produto);
    }

    public void deleteProduto(String codigoDeBarras) {
        produtoRepository.deleteByCodigoDeBarras(codigoDeBarras);
    }
}
