package com.bd.infra;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Login {
    private String user;
    private String senha;

    private static class SingletonHelper {
        private static final Login INSTANCE = new Login();
    }

    public static Login getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
