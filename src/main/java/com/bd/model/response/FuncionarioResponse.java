package com.bd.model.response;

public record FuncionarioResponse(
        Integer codigo,
        String nome,
        String cpf,
        String funcao) { }
