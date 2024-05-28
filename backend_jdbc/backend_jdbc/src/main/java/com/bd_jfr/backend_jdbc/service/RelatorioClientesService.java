package com.bd_jfr.backend_jdbc.service;

import com.bd_jfr.backend_jdbc.model.ClienteCompras;
import com.bd_jfr.backend_jdbc.repository.RelatorioClientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioClientesService {
    private final RelatorioClientesRepository relatorioClientesRepository;

    public RelatorioClientesService(RelatorioClientesRepository relatorioClientesRepository) {
        this.relatorioClientesRepository = relatorioClientesRepository;
    }

    public List<ClienteCompras> findClientesQueMaisCompraram() {
        return relatorioClientesRepository.findClientesQueMaisCompraram();
    }
}
