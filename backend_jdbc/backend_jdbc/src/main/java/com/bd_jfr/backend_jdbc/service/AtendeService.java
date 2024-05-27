package com.bd_jfr.backend_jdbc.service;
import com.bd_jfr.backend_jdbc.model.Atende;
import com.bd_jfr.backend_jdbc.repository.AtendeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AtendeService {
    private final AtendeRepository atendeRepository;

    public AtendeService(AtendeRepository atendeRepository) {
        this.atendeRepository = atendeRepository;
    }

    public List<Atende> findAll() {
        return atendeRepository.findAll();
    }

    public void save(Atende atende) {
        atendeRepository.save(atende);
    }
}
