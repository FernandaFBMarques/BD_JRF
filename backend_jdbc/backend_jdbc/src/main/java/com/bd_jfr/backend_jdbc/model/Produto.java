package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    private String codigoDeBarras;
    private String nomeProduto;
    private String modelo;
    private String cor;
    private int tamanho;
    private String categoria;
    private double preco;
    private java.sql.Date prazo;
    private String urlImagem;
}
