package com.bd.repository;

import com.bd.infra.Conexao;
import com.bd.model.Fornecedor;
import com.bd.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FornecedorRepository {
    public Fornecedor cadastrarFornecedor(Fornecedor fornecedor) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT MAX(for_codigo) as for_codigo FROM tb_fornecedores";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultado = statement.executeQuery();
                if (resultado.next()) {
                    fornecedor.setFor_codigo(resultado.getInt("for_codigo") + 1);
                }
            }

            sql = "INSERT INTO tb_fornecedores (for_codigo, for_descricao) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, fornecedor.getFor_codigo());
                statement.setString(2, fornecedor.getFor_descricao());
                statement.executeUpdate();
            }

            return fornecedor;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar fornecedor", ex);
        }
    }

    public List<Fornecedor> buscarFornecedores() {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT for_codigo, for_descricao FROM tb_fornecedores";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultado = statement.executeQuery();
                List<Fornecedor> fornecedores = new ArrayList<>();
                while (resultado.next()) {
                    Fornecedor fornecedor = new Fornecedor(
                            resultado.getInt("for_codigo"),
                            resultado.getString("for_descricao")
                    );
                    fornecedores.add(fornecedor);
                }
                return fornecedores;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar fornecedores", ex);
        }
    }

    public Fornecedor buscarFornecedorPeloId(Long id) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT fornecedor_for_codigo, fornecedor_for_descricao FROM tb_fornecedores = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                ResultSet resultado = statement.executeQuery();
                if (resultado.next()) {
                    return new Fornecedor(
                            resultado.getInt("for_codigo"),
                            resultado.getString("for_descricao")
                    );
                }
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar fornecedor", ex);
        }
    }

    public Fornecedor atualizarFornecedor(Long id, Fornecedor fornecedor){
        try (Connection connection = Conexao.getConnection()) {
            String sql = "UPDATE tb_fornecedor SET for_codigo = ?, for_descricao = ?, WHERE for_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, fornecedor.getFor_codigo());
                statement.setString(2, fornecedor.getFor_descricao());
                statement.executeUpdate();
            }
            return fornecedor;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar fornecedor", ex);
        }
    }
    public boolean deletarFornecedor(Long id) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "DELETE FROM tb_fornecedores WHERE for_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar fornecedor", ex);
        }
    }



}

