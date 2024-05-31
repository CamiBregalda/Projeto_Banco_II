package com.bd.repository;

import com.bd.infra.Conexao;
import com.bd.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    public void cadastrarUsuario(Usuario user) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "INSERT INTO usuarios (user_nome, user_cpf, user_username, user_senha) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getNome());
                statement.setString(2, user.getCpf());
                statement.setString(4, user.getUsername());
                statement.setString(5, user.getSenha());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar usuário", ex);
        }
    }

    public List<Usuario> buscarUsuarios() {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT user_codigo, user_nome, user_cpf, user_username FROM tb_usuario";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultado = statement.executeQuery();
                List<Usuario> usuarios = new ArrayList<>();
                while (resultado.next()) {
                    Usuario usuario = new Usuario(
                            resultado.getInt("user_codigo"),
                            resultado.getString("user_nome"),
                            resultado.getString("user_cpf"),
                            resultado.getString("user_username")
                    );
                    usuarios.add(usuario);
                }
                return usuarios;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar usuários", ex);
        }
    }

    public Usuario buscarUsuarioPeloId(Long id) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT user_codigo, user_nome, user_cpf, user_username FROM tb_usuario WHERE user_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                ResultSet resultado = statement.executeQuery();
                if (resultado.next()) {
                    return new Usuario(
                            resultado.getInt("user_codigo"),
                            resultado.getString("user_nome"),
                            resultado.getString("user_cpf"),
                            resultado.getString("user_username")
                    );
                }
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar usuário", ex);
        }
    }
}
