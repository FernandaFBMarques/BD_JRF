package com.bd_jfr.backend_jdbc.ClienteDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteDTO {
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
    private List<String> emails;
}
