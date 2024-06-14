package com.bd.repository;

import com.bd.infra.Conexao;
import com.bd.model.Produto;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoRepository {
    public Produto cadastrarProduto(Produto produto) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT MAX(pro_codigo) as pro_codigo FROM tb_produtos";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultado = statement.executeQuery();
                if (resultado.next()) {
                    produto.setPro_codigo(resultado.getInt("pro_codigo") + 1);
                }
            }

            sql = "INSERT INTO tb_produtos (pro_codigo, pro_descricao, pro_valor, pro_quantidade, tb_fornecedores_for_codigo) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, produto.getPro_codigo());
                statement.setString(2, produto.getPro_descricao());
                statement.setDouble(3, produto.getPro_valor());
                statement.setInt(4, produto.getPro_quantidade());
                statement.setInt(5, produto.getTb_fornecedores_for_codigo());
                statement.executeUpdate();
            }

            return produto;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar produto", ex);
        }
    }

    public List<Produto> buscarProdutos() {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT pro_codigo, pro_descricao, pro_valor, pro_quantidade, tb_fornecedores_for_codigo FROM tb_produtos";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultado = statement.executeQuery();
                List<Produto> produtos = new ArrayList<>();
                while (resultado.next()) {
                    Produto produto = new Produto(
                            resultado.getInt("pro_codigo"),
                            resultado.getString("pro_descricao"),
                            resultado.getDouble("pro_valor"),
                            resultado.getInt("pro_quantidade"),
                            resultado.getInt("tb_fornecedores_for_codigo")
                    );
                    produtos.add(produto);
                }
                return produtos;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar produtos", ex);
        }
    }

    public Produto buscarProdutoPeloId(Long id) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT pro_codigo, pro_descricao, pro_valor, pro_quantidade, tb_fornecedores_for_codigo FROM tb_produtos WHERE pro_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                ResultSet resultado = statement.executeQuery();
                if (resultado.next()) {
                    return new Produto(
                            resultado.getInt("pro_codigo"),
                            resultado.getString("pro_descricao"),
                            resultado.getDouble("pro_valor"),
                            resultado.getInt("pro_quantidade"),
                            resultado.getInt("tb_fornecedores_for_codigo")
                    );
                }
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar produto", ex);
        }
    }

    public Produto atualizarProduto(Long id, Produto produto){
        try (Connection connection = Conexao.getConnection()) {
            String sql = "UPDATE tb_produtos SET pro_descricao = ?, pro_valor = ?,  pro_quantidade = ? WHERE pro_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, produto.getPro_descricao());
                statement.setDouble(2, produto.getPro_valor());
                statement.setLong(3, produto.getPro_quantidade());
                statement.setLong(4, id);
                statement.executeUpdate();
            }
            return produto;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar produto", ex);
        }
    }

    public boolean deletarProduto(Long id) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "DELETE FROM tb_produtos WHERE pro_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar produto", ex);
        }
    }


}
