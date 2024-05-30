package com.bd_jfr.backend_jdbc.service;

import com.bd_jfr.backend_jdbc.model.FazVendaProduto;
import com.bd_jfr.backend_jdbc.repository.FazVendaProdutoRepository;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
public class FazVendaProdutoService {
    private final FazVendaProdutoRepository fazVendaProdutoRepository;

    public FazVendaProdutoService(FazVendaProdutoRepository fazVendaProdutoRepository) {
        this.fazVendaProdutoRepository = fazVendaProdutoRepository;
    }

    public List<FazVendaProduto> findAll() {
        return fazVendaProdutoRepository.findAll();
    }

    public void save(FazVendaProduto fazVendaProduto) throws SQLException {
        fazVendaProdutoRepository.save(fazVendaProduto);
    }

    public void updateQuantidade(int fkVendaNumeroVenda, String fkProdutosCodigoDeBarras, int quantidadeDeProduto) throws SQLException {
        fazVendaProdutoRepository.updateQuantidade(fkVendaNumeroVenda, fkProdutosCodigoDeBarras, quantidadeDeProduto);
    }
}
