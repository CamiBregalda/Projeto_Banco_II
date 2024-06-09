package com.bd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fun_codigo;
    private String fun_nome;
    private String fun_cpf;
    private String fun_senha;
    private String fun_funcao;


    public Funcionario(Integer fun_codigo, String fun_nome, String fun_cpf, String fun_funcao) {
        this.fun_codigo = fun_codigo;
        this.fun_nome = fun_nome;
        this.fun_cpf = fun_cpf;
        this.fun_funcao = fun_funcao;
    }
}
