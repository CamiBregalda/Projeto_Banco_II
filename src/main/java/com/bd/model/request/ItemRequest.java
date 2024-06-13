package com.bd.model.request;

public record ItemRequest(
        Integer ite_codigo,
        Integer ite_quantidade,
        Double ite_valorParcial,
        Integer ite_produtosCodigo,
        Integer ite_vendasCodigo )
{ }
