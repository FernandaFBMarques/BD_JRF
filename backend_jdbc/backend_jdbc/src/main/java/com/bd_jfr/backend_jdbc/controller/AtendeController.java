package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.Atende;
import com.bd_jfr.backend_jdbc.service.AtendeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/atende")
public class AtendeController {
    private final AtendeService atendeService;

    @Autowired
    public AtendeController(AtendeService atendeService) {
        this.atendeService = atendeService;
    }

    @GetMapping
    public List<Atende> findAll() {
        return atendeService.findAll();
    }

    @PostMapping
    public void create(@RequestBody Atende atende) {
        atendeService.save(atende);
    }
}
