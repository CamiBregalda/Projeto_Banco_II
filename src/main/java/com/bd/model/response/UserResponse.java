package com.bd.model.response;

public record UserResponse(
        Integer codigo,
        String nome,
        String cpf,
        String username
) {}

