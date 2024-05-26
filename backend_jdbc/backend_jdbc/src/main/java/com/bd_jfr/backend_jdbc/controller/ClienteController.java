package com.bd_jfr.backend_jdbc.controller;

import com.bd_jfr.backend_jdbc.ClienteDTO.ClienteDTO;
import com.bd_jfr.backend_jdbc.model.Cliente;
import com.bd_jfr.backend_jdbc.service.ClienteService;
import com.bd_jfr.backend_jdbc.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @PostMapping("/addCliente")
    public void addCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getCnpj(), clienteDTO.getNomeLoja(), clienteDTO.getTelefone(), clienteDTO.getEmail(), clienteDTO.getRua(), clienteDTO.getNumero(), clienteDTO.getCidade(), clienteDTO.getBairro(), clienteDTO.getEstado(), clienteDTO.isTipoVarejo(), clienteDTO.isTipoBoutique(), 0);
        clienteService.addCliente(cliente, clienteDTO.getEmails());
    }

    @PutMapping("/updateCliente")
    public void updateCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getCnpj(), clienteDTO.getNomeLoja(), clienteDTO.getTelefone(), clienteDTO.getEmail(), clienteDTO.getRua(), clienteDTO.getNumero(), clienteDTO.getCidade(), clienteDTO.getBairro(), clienteDTO.getEstado(), clienteDTO.isTipoVarejo(), clienteDTO.isTipoBoutique(), 0);
        clienteService.updateCliente(cliente, clienteDTO.getEmails());
    }
}


