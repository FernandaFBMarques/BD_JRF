package com.bd_jfr.backend_jdbc.service;

import com.bd_jfr.backend_jdbc.model.RelatorioVendasEquipe;
import com.bd_jfr.backend_jdbc.repository.RelatorioVendasEquipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioVendasEquipeService {
    private final RelatorioVendasEquipeRepository relatorioVendasEquipeRepository;

    public RelatorioVendasEquipeService(RelatorioVendasEquipeRepository relatorioVendasEquipeRepository) {
        this.relatorioVendasEquipeRepository = relatorioVendasEquipeRepository;
    }

    public List<RelatorioVendasEquipe> findRelatorioVendasEquipe() {
        return relatorioVendasEquipeRepository.findRelatorioVendasEquipe();
    }

    public List<RelatorioVendasEquipe> getVendasPorMembro() {
        return relatorioVendasEquipeRepository.findRelatorioVendasEquipe();
    }
}
