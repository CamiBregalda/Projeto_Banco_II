package com.bd.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Funcionario {
    private Integer codigo;
    private String nome;
    private String cpf;
    private String senha;
    private String funcao;
}
