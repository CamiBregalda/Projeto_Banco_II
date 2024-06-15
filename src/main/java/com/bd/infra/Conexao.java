package com.bd.infra;

import com.bd.exception.InvalidLoginException;

import java.sql.*;

public class Conexao {
    public static Connection getConnection(){
        try {
            String user = Login.getInstance().getUser();
            String senha = Login.getInstance().getSenha();

            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/loja_virtual", user, senha);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", ex);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
            }
        }
    }
}
