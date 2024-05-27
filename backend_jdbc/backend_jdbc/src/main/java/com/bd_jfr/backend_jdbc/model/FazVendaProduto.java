package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FazVendaProduto {
    private int fkVendaNumeroVenda;
    private String fkProdutosCodigoDeBarras;
    private int fkAtendeNumeroAtendimento;
    private int quantidadeDeProduto;
}
