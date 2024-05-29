package com.bd.repository;

import com.bd.infra.Conexao;
import com.bd.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UsuarioRepository {

    public void cadastrarUsuario(Usuario user) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "INSERT INTO usuarios (user_nome, user_cpf, user_funcao, user_username, user_senha) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getNome());
                statement.setString(2, user.getCpf());
                statement.setString(3, user.getFuncao());
                statement.setString(4, user.getUsername());
                statement.setString(5, user.getSenha());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar usuário", ex);
        }
    }
}
