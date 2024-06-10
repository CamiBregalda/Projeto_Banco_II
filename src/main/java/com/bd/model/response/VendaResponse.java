package com.bd.model.response;

import java.time.LocalDateTime;

public record VendaResponse(

        Integer codigo,
        LocalDateTime horario,
        Double valorTotal,
        Integer funcionariosCodigo)
{ }
