package com.bd.repository;

import com.bd.infra.Conexao;
import com.bd.model.Item;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository {

    public Item cadastrarItem(Item item) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT MAX(ite_codigo) as ite_codigo FROM tb_itens";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultado = statement.executeQuery();
                if (resultado.next()) {
                    item.setIte_codigo(resultado.getInt("ite_codigo") + 1);
                }
            }

            sql = "INSERT INTO tb_itens (ite_codigo, ite_quantidade, ite_valor_parcial, tb_produtos_pro_codigo, tb_vendas_ven_codigo) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, item.getIte_codigo());
                statement.setInt(2, item.getIte_quantidade());
                statement.setDouble(3, item.getIte_valor_parcial());
                statement.setInt(4, item.getTb_produtos_pro_codigo());
                statement.setInt(5, item.getTb_vendas_ven_codigo());
                statement.executeUpdate();
            }

            return item;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar item", ex);
        }
    }

    public List<Item> buscarItens() {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT ite_codigo, ite_quantidade, ite_valor_parcial, tb_produtos_pro_codigo, tb_vendas_ven_codigo FROM tb_itens";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultado = statement.executeQuery();
                List<Item> itens = new ArrayList<>();
                while (resultado.next()) {
                    Item item = new Item(
                            resultado.getInt("ite_codigo"),
                            resultado.getInt("ite_quantidade"),
                            resultado.getDouble("ite_valor_parcial"),
                            resultado.getInt("tb_produtos_pro_codigo"),
                            resultado.getInt("tb_vendas_ven_codigo")
                    );
                    itens.add(item);
                }
                return itens;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar itens", ex);
        }
    }

    public Item buscarItemPeloId(Long id) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT ite_codigo, ite_quantidade, ite_valor_parcial, tb_produtos_pro_codigo, tb_vendas_ven_codigo FROM tb_itens WHERE ite_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                ResultSet resultado = statement.executeQuery();
                if (resultado.next()) {
                    return new Item(
                            resultado.getInt("ite_codigo"),
                            resultado.getInt("ite_quantidade"),
                            resultado.getDouble("ite_valor_parcial"),
                            resultado.getInt("tb_produtos_pro_codigo"),
                            resultado.getInt("tb_vendas_ven_codigo")
                    );
                }
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar item", ex);
        }
    }

    public Item atualizarItem(Long id, Item item){
        try (Connection connection = Conexao.getConnection()) {
            String sql = "UPDATE tb_itens SET ite_quantidade = ?, ite_valor_parcial = ? WHERE ven_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, item.getIte_quantidade());
                statement.setDouble(2, item.getIte_valor_parcial());
                statement.setLong(3, id);
                statement.executeUpdate();
            }
            return item;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar item", ex);
        }
    }

    public boolean deletarItem(Long id) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "DELETE FROM tb_itens WHERE ite_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar item", ex);
        }
    }

}
