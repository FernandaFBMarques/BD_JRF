package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.model.NFDeComissionamento;
import com.bd_jfr.backend_jdbc.service.NFDeComissionamentoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/nfDeComissionamento")
public class NFDeComissionamentoController {
    private final NFDeComissionamentoService nfDeComissionamentoService;

    @Autowired
    public NFDeComissionamentoController(NFDeComissionamentoService nfDeComissionamentoService) {
        this.nfDeComissionamentoService = nfDeComissionamentoService;
    }

    @GetMapping
    public List<NFDeComissionamento> findAll() {
        return nfDeComissionamentoService.findAll();
    }

    @PostMapping
    public void create(@RequestBody NFDeComissionamento nfDeComissionamento) {
        nfDeComissionamentoService.save(nfDeComissionamento);
    }
}