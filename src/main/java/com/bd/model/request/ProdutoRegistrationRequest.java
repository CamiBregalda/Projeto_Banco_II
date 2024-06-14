package com.bd.model.request;

import lombok.Data;

@Data
public class ProdutoRegistrationRequest {
    private ProdutoRequest produtoRequest;
    private UserLoginDTO userLoginDTO;
}
