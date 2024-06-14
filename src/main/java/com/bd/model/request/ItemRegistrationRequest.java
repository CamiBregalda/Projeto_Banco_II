package com.bd.model.request;

import lombok.Data;

@Data
public class ItemRegistrationRequest {
    private ItemRequest itemRequest;
    private UserLoginDTO userLoginDTO;
}
