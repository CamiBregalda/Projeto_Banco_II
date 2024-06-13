package com.bd.model.request;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public record VendaRequest(
        Integer codigo,
        LocalDateTime horario,
        Double valorTotal,
        Integer funcionariosCodigo)
{ }
