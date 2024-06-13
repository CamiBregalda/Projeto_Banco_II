package com.bd.model.request;

public record FuncionarioRequest(

        Integer fun_codigo,
        String fun_nome,
        String fun_cpf,
        String fun_funcao,
        String fun_senha

)

{ }
