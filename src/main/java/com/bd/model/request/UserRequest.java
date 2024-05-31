package com.bd.model.request;

public record UserRequest(
        Integer codigo,
        String nome,
        String cpf,
        String username,
        String senha
) {}
