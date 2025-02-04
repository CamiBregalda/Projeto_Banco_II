package com.bd.repository;

import com.bd.infra.Conexao;
import com.bd.model.Venda;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VendaRepository {

    public Venda cadastrarVenda(Venda venda) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT MAX(ven_codigo) as ven_codigo FROM tb_vendas";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultado = statement.executeQuery();
                if (resultado.next()) {
                    venda.setVen_codigo(resultado.getInt("ven_codigo") + 1);
                }
            }

            sql = "INSERT INTO tb_vendas (ven_codigo, ven_horario, ven_valor_total, tb_funcionarios_fun_codigo) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, venda.getVen_codigo());
                statement.setTimestamp(2, Timestamp.valueOf(venda.getVen_horario()));
                statement.setDouble(3, venda.getVen_valor_total());
                statement.setInt(4, venda.getTb_funcionarios_fun_codigo());
                statement.executeUpdate();
            }

            return venda;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar venda", ex);
        }
    }

    public List<Venda> buscarVendas() {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT ven_codigo, ven_horario, ven_valor_total, tb_funcionarios_fun_codigo FROM tb_vendas";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultado = statement.executeQuery();
                List<Venda> vendas = new ArrayList<>();
                while (resultado.next()) {
                    Venda venda = new Venda(
                            resultado.getInt("ven_codigo"),
                            resultado.getTimestamp("ven_horario").toLocalDateTime(),
                            resultado.getDouble("ven_valor_total"),
                            resultado.getInt("tb_funcionarios_fun_codigo")
                    );
                    vendas.add(venda);
                }
                return vendas;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar vendas", ex);
        }
    }

    public Venda buscarVendaPeloId(Long id) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT ven_codigo, ven_horario, ven_valor_total, tb_funcionarios_fun_codigo FROM tb_vendas WHERE ven_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                ResultSet resultado = statement.executeQuery();
                if (resultado.next()) {
                    return new Venda(
                            resultado.getInt("ven_codigo"),
                            resultado.getTimestamp("ven_horario").toLocalDateTime(),
                            resultado.getDouble("ven_valor_total"),
                            resultado.getInt("tb_funcionarios_fun_codigo")
                    );
                }
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar venda", ex);
        }
    }

    public Venda atualizarVenda(Long id, Venda venda){
        try (Connection connection = Conexao.getConnection()) {
            String sql = "UPDATE tb_vendas SET ven_horario = ?, ven_valor_total = ? WHERE ven_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setTimestamp(1, Timestamp.valueOf(venda.getVen_horario()));
                statement.setDouble(2, venda.getVen_valor_total());
                statement.setLong(3, id);
                statement.executeUpdate();
            }
            return venda;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar venda", ex);
        }
    }

    public boolean deletarVenda(Long id) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "DELETE FROM tb_vendas WHERE ven_codigo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar venda", ex);
        }
    }

    public String realizarVenda(long funcionario_codigo, long produto_codigo, int quantidade_venda) {
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT realizarVenda(?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, funcionario_codigo);
                statement.setLong(2, produto_codigo);
                statement.setInt(3, quantidade_venda);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        String result = rs.getString(1);
                        return result;
                    } else {
                        throw new RuntimeException("Erro ao realizar venda: Função não retornou resultado.");
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao realizar venda", ex);
        }
    }
}
