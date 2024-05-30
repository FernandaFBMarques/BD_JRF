package com.bd_jfr.backend_jdbc.service;

import com.bd_jfr.backend_jdbc.model.NFDeComissionamento;
import com.bd_jfr.backend_jdbc.repository.NFDeComissionamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NFDeComissionamentoService {
    private final NFDeComissionamentoRepository nfDeComissionamentoRepository;

    public NFDeComissionamentoService(NFDeComissionamentoRepository nfDeComissionamentoRepository) {
        this.nfDeComissionamentoRepository = nfDeComissionamentoRepository;
    }

    public List<NFDeComissionamento> findAll() {
        return nfDeComissionamentoRepository.findAll();
    }

    public void save(NFDeComissionamento nf) {
        nfDeComissionamentoRepository.save(nf);
    }
}
