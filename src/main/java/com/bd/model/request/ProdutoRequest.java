package com.bd.model.request;

public record ProdutoRequest(
        Integer pro_codigo,
        String pro_descricao,
        double pro_valor,
        Integer pro_quantidade,
        Integer tb_fornecedores_for_codigo) {
}
