package com.bd.model.request;

import lombok.Data;

@Data
public class VendaRegistrationRequest {

    private VendaRequest vendaRequest;
    private UserLoginDTO userLoginDTO;
}
