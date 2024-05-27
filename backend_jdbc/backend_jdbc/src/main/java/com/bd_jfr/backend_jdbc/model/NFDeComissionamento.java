package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class NFDeComissionamento {
    private int numero;
    private String fkContadoresCpf;
    private int fkVendaNumeroVenda;
    private String fkEquipeDeVendasCpf;
    private int serie;
    private Date dataEmissao;
    private Date dataSaida;
    private double calculoDoImposto;
}