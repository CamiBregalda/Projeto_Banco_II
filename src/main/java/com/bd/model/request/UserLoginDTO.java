package com.bd.model.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
public class UserLoginDTO {
    private String username;
    private String password;

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
