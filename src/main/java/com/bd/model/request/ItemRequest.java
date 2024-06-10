package com.bd.model.request;

public record ItemRequest(
        Integer codigo,
        Integer quantidade,
        Double valorParcial,
        Integer produtosCodigo,
        Integer vendasCodigo )
{ }
