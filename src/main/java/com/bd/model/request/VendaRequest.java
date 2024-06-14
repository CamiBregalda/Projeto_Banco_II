package com.bd.model.request;
import java.time.LocalDateTime;

public record VendaRequest(
        Integer ven_codigo,
        LocalDateTime ven_horario,
        Double ven_valor_total,
        Integer tb_funcionarios_fun_codigo
)
{}
