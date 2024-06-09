package com.bd.model.request;

public record FuncionarioRequest(

        Integer codigo,
        String nome,
        String cpf,
        String senha,
        String funcao) { }
