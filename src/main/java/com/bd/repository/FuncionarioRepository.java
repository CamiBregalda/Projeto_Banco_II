package com.bd.repository;

import com.bd.infra.Conexao;
import com.bd.model.Funcionario;
import com.bd.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FuncionarioRepository {

    public Funcionario cadastrarFuncionario(Funcionario funcionario) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT MAX(fun_codigo) as fun_codigo FROM tb_funcionarios";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultado = statement.executeQuery();
                if (resultado.next()) {
                    funcionario.setFun_codigo(resultado.getInt("fun_codigo") + 1);
                }
            }

            sql = "INSERT INTO tb_funcionarios (fun_codigo, fun_nome, fun_cpf, fun_funcao, fun_senha) VALUES ( ?, ?, ?, ?, ?)";
            System.out.println(funcionario.toString());
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, funcionario.getFun_codigo());
                statement.setString(2, funcionario.getFun_nome());
                statement.setString(3, funcionario.getFun_cpf());
                statement.setString(4, funcionario.getFun_funcao());
                statement.setString(5, funcionario.getFun_senha());
                statement.executeUpdate();
            }

            return funcionario;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar funcionario", ex);
        }
    }

    public List<Funcionario> buscarFuncionarios() {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT fun_codigo, fun_nome, fun_cpf, fun_funcao FROM tb_funcionarios";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultado = statement.executeQuery();
                List<Funcionario> funcionarios = new ArrayList<>();
                while (resultado.next()) {
                    Funcionario funcionario = new Funcionario(
                            resultado.getInt("fun_codigo"),
                            resultado.getString("fun_nome"),
                            resultado.getString("fun_cpf"),
                            resultado.getString("fun_funcao")
                    );
                    funcionarios.add(funcionario);
                }
                return funcionarios;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar funcionarios", ex);
        }
    }

    public Funcionario buscarFuncionarioPeloId(Long id) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT fun_codigo, fun_nome, fun_cpf, fun_funcao FROM tb_funcionarios WHERE fun_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                ResultSet resultado = statement.executeQuery();
                if (resultado.next()) {
                    return new Funcionario(
                            resultado.getInt("fun_codigo"),
                            resultado.getString("fun_nome"),
                            resultado.getString("fun_cpf"),
                            resultado.getString("fun_funcao")
                    );
                }
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar funcionario", ex);
        }
    }

    public Funcionario atualizarFuncionario(Long id, Funcionario funcio){
        try (Connection connection = Conexao.getConnection()) {
            String sql = "UPDATE tb_funcionarios SET fun_nome = ?, fun_cpf = ?, fun_funcao = ?, fun_senha = ? WHERE fun_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, funcio.getFun_nome());
                statement.setString(2, funcio.getFun_cpf());
                statement.setString(3, funcio.getFun_funcao());
                statement.setString(4, funcio.getFun_senha());
                statement.setLong(5, id);
                statement.executeUpdate();
            }
            return funcio;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar funcionario", ex);
        }
    }

    public boolean deletarFuncionario(Long id) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "DELETE FROM tb_funcionarios WHERE fun_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar funcionario", ex);
        }
    }

    public void cadastrarRole (String role,  String[] usernames) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT cadastrar_role(?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                Array array = connection.createArrayOf("VARCHAR", usernames);
                statement.setString(1, role);
                statement.setArray(2, array);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar role", e);
        }
    }

    public void atualizarUsersRole (String role,  String[] usernames) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT atualizar_users_role(?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                Array array = connection.createArrayOf("VARCHAR", usernames);
                statement.setString(1, role);
                statement.setArray(2, array);
                statement.executeUpdate();
            }
        }catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar role", e);
        }

    }

    public ArrayList<String> buscarRoles() {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT rolname FROM pg_roles";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                ResultSet resultado = statement.executeQuery();
                ArrayList<String> roles = new ArrayList<>();
                while (resultado.next()) {
                    roles.add(resultado.getString("rolname"));
                }
                return roles;
            }
        } catch (SQLException e){
            throw new RuntimeException("Erro ao mostrar roles", e);
        }
    }

    public void concederPrivilegioGrupo (String nameGrupo,  String nomeDaTabela, String[] privilegios) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT conceder_privilegio_grupo(?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                Array array = connection.createArrayOf("VARCHAR", privilegios);
                statement.setString(1, nameGrupo);
                statement.setString(2, nomeDaTabela);
                statement.setArray(3, array);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conceder privilegio ao grupo", e);
        }
    }

    public void concederPrivilegioUsuario (String nameUsuario,  String nomeDaTabela, String[] privilegios) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT conceder_privilegio_usuario(?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                Array array = connection.createArrayOf("VARCHAR", privilegios);
                statement.setString(1, nameUsuario);
                statement.setString(2, nomeDaTabela);
                statement.setArray(3, array);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conceder privilegio ao grupo", e);
        }
    }





}
