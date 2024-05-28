package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoVendaEstado {
    private String nomeProduto;
    private String estado;
    private int totalVendas;
}
