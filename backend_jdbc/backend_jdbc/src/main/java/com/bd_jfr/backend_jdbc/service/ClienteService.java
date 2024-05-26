package com.bd_jfr.backend_jdbc.service;

import com.bd_jfr.backend_jdbc.model.Cliente;
import com.bd_jfr.backend_jdbc.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public void addCliente(Cliente cliente, List<String> emails) {
        clienteRepository.save(cliente, emails);
    }

    public void updateCliente(Cliente cliente, List<String> emails) {
        clienteRepository.update(cliente, emails);
    }

    public void deleteCliente(String cnpj) {
        clienteRepository.delete(cnpj);
    }
}
