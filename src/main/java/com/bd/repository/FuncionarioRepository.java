package com.bd.repository;

import com.bd.infra.Conexao;
import com.bd.model.Funcionario;
import com.bd.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FuncionarioRepository {


    public Funcionario cadastrarFuncionario(Funcionario funcio) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "INSERT INTO tb_funcionarios (fun_codigo, fun_nome, fun_cpf, fun_funcao, fun_senha) VALUES ( ?, ?, ?, ?, ?)";
            System.out.println(funcio.toString());
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, funcio.getFun_codigo());
                statement.setString(2, funcio.getFun_nome());
                statement.setString(3, funcio.getFun_cpf());
                statement.setString(4, funcio.getFun_funcao());
                statement.setString(5, funcio.getFun_senha());

                statement.executeUpdate();
            }

            return funcio;
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

}
