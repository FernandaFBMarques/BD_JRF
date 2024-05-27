package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatorioVenda {
    private int numeroVenda;
    private double totalVenda;
    private int numeroNf;
    private String vendedor;
    private String cliente;
    private double calculoDoImposto;
}