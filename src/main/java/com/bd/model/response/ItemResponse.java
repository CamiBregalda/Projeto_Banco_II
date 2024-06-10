package com.bd.model.response;

public record ItemResponse(

        Integer codigo,
        Integer quantidade,
        Double valorParcial,
        Integer produtosCodigo,
        Integer vendasCodigo)
{ }
