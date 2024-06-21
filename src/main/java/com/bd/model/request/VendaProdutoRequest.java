package com.bd.model.request;
import java.time.LocalDateTime;

public record VendaProdutoRequest(
    Integer tb_funcionarios_fun_codigo,
    Integer pro_codigo,
    Integer pro_quantidade        
)
{}
