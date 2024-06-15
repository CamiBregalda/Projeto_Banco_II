package com.bd.model.response;
import java.time.LocalDateTime;

public record VendaResponse(
        Integer ven_codigo,
        LocalDateTime ven_horario,
        Double ven_valor_total,
        Integer tb_funcionarios_fun_codigo
)
{}
