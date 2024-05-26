package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private String cnpj;
    private String nomeLoja;
    private String telefone;
    private String email;
    private String rua;
    private int numero;
    private String cidade;
    private String bairro;
    private String estado;
    private boolean tipoVarejo;
    private boolean tipoBoutique;
    private int totalEmailsAlternativos; // Novo campo para armazenar o total de emails alternativos
}