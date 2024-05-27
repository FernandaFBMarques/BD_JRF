package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatorioVendasEquipe {
    private String nomeVendedor;
    private double totalVendas;
    private double totalImpostos;
    private String nomeGerente;
}
