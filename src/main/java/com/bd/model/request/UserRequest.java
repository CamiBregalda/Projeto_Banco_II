package com.bd.model.request;

public record UserRequest(
        Integer user_codigo,
        String user_nome,
        String user_cpf,
        String user_username,
        String user_senha
) {}
