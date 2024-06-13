package com.bd.model.response;

public record UserResponse(
        Integer user_codigo,
        String user_nome,
        String user_cpf,
        String user_username
) {}

