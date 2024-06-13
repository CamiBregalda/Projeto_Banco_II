package com.bd.model.request;

import lombok.Data;

@Data
public class FornecedorRegistrationRequest {
    private FornecedorRequest fornecedorRequest;
    private UserLoginDTO userLoginDTO;
}
