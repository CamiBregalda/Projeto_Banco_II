package com.bd.model.response;

public record FuncionarioResponse(
        Integer fun_codigo,
        String fun_nome,
        String fun_cpf,
        String fun_funcao) { }
