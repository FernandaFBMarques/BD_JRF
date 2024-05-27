package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipeDeVendas {
    private String fkFuncionariosCpf;
    private String cnpj;
    private String matGerente;
}