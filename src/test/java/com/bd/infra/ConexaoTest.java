package com.bd.infra;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConexaoTest {

    private static Login login;

    @BeforeAll
    public static void setUp() {
        login = Login.getInstance();
        login.setUser("valid_user");
        login.setSenha("valid_password");
    }

    @Test
    public void testGetConnectionSuccess() {
        Connection connection = null;
        try {
            connection = Conexao.getConnection();
        } finally {
            Conexao.closeConnection(connection);
        }
    }

    @Test
    public void testCloseConnectionSuccess() {
        Connection connection = null;

        connection = Conexao.getConnection();
        Conexao.closeConnection(connection);
    }

    @Test
    public void testGetConnectionFailure() {
        login.setUser("invalid_user");
        login.setSenha("invalid_password");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            Conexao.getConnection();
        });

        assertTrue(exception.getMessage().contains("Erro ao conectar com o banco de dados"));
    }
}
