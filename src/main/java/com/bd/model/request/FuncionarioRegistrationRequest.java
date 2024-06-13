package com.bd.model.request;

import lombok.Data;

@Data
public class FuncionarioRegistrationRequest {

    private FuncionarioRequest funcionarioRequest;
    private UserLoginDTO userLoginDTO;

}
