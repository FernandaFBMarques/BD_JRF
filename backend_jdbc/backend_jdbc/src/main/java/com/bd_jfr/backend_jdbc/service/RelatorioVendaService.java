package com.bd_jfr.backend_jdbc.service;

import com.bd_jfr.backend_jdbc.model.RelatorioVenda;
import com.bd_jfr.backend_jdbc.repository.RelatorioVendaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RelatorioVendaService {
    private final RelatorioVendaRepository relatorioVendaRepository;

    public RelatorioVendaService(RelatorioVendaRepository relatorioVendaRepository) {
        this.relatorioVendaRepository = relatorioVendaRepository;
    }

    public List<RelatorioVenda> findRelatorioVendas() {
        return relatorioVendaRepository.findRelatorioVendas();
    }
}
