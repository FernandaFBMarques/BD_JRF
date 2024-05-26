package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private int idEmail;
    private String fkClientesCnpj;
    private String email;
}