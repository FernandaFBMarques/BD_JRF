package com.bd_jfr.backend_jdbc.service;

import com.bd_jfr.backend_jdbc.model.Venda;
import com.bd_jfr.backend_jdbc.repository.VendaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendaService {
    private final VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    public void save(Venda venda) {
        vendaRepository.save(venda);
    }
}
