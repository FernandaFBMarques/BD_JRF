package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Funcionario {
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private String rua;
    private int numero;
    private String cidade;
    private String bairro;
    private String cargo;
    private boolean estaAtivo = true;

    // Adicionar relações com Contador e EquipeDeVendas
    private Contador contador;
    private EquipeDeVendas equipeDeVendas;
}