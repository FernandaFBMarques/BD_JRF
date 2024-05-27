package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dependente {
    private String cpf;
    private String fkContadoresFkFuncionariosCpf;
    private String nome;
}
