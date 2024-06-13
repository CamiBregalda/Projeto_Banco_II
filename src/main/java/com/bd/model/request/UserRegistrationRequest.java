package com.bd.model.request;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private UserRequest userRequest;
    private UserLoginDTO userLoginDTO;
}
