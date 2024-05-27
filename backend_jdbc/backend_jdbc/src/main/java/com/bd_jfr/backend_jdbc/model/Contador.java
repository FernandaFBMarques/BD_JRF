package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Contador {
    private String fkFuncionariosCpf;
    private String clt;
    private List<Dependente> dependentes;
}