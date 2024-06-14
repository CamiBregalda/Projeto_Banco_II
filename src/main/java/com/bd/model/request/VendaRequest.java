package com.bd.model.request;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public record VendaRequest(
        Integer ven_codigo,
        LocalDateTime ven_horario,
        Double ven_valor_total,
        Integer tb_funcionarios_fun_codigo)
{ }
