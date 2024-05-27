package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Atende {
    private int numeroAtendimento;
    private String fkEquipeDeVendasCpf;
    private String fkClientesCnpj;
}