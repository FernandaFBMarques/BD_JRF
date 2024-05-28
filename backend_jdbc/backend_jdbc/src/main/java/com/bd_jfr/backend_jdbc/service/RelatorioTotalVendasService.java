package com.bd_jfr.backend_jdbc.service;

import com.bd_jfr.backend_jdbc.model.RelatorioTotalVendas;
import com.bd_jfr.backend_jdbc.repository.RelatorioTotalVendasRepository;
import org.springframework.stereotype.Service;

@Service
public class RelatorioTotalVendasService {
    private final RelatorioTotalVendasRepository relatorioTotalVendasRepository;

    public RelatorioTotalVendasService(RelatorioTotalVendasRepository relatorioTotalVendasRepository) {
        this.relatorioTotalVendasRepository = relatorioTotalVendasRepository;
    }

    public RelatorioTotalVendas findRelatorioTotalVendas() {
        return relatorioTotalVendasRepository.findRelatorioTotalVendas();
    }

    public double getTotalVendasSemImposto() {
        return relatorioTotalVendasRepository.findRelatorioTotalVendas().getTotalVendas();
    }

    public double getTotalVendasComImposto() {
        return relatorioTotalVendasRepository.findRelatorioTotalVendas().getTotalVendasComImposto();
    }
}
