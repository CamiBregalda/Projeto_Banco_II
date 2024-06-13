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

    public Usuario cadastrarUsuario(Usuario user) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "INSERT INTO usuarios (user_nome, user_cpf, user_username, user_senha) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, user.getUser_codigo());
                statement.setString(2, user.getUser_nome());
                statement.setString(3, user.getUser_cpf());
                statement.setString(4, user.getUser_username());
                statement.setString(5, user.getUser_senha());
                statement.executeUpdate();
            }

            return user;
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

    public Usuario atualizarUsuario(Long id, Usuario user){
        try (Connection connection = Conexao.getConnection()) {
            String sql = "UPDATE tb_usuario SET user_nome = ?, user_cpf = ?, user_username = ?, user_senha = ? WHERE user_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getUser_nome());
                statement.setString(2, user.getUser_cpf());
                statement.setString(3, user.getUser_username());
                statement.setString(4, user.getUser_senha());
                statement.setLong(5, id);
                statement.executeUpdate();
            }
            return user;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar usuário", ex);
        }
    }

    public boolean deletarUsuario(Long id) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "DELETE FROM tb_usuario WHERE user_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar usuário", ex);
        }
    }
}
