package com.bd_jfr.backend_jdbc.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class Venda {
    private int numeroVenda;
    private Date data;
    private double valorVenda;
}