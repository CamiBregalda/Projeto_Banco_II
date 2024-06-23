package com.bd.repository;

import com.bd.infra.Conexao;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class BackupRepository {

    public void programarBackup(Timestamp novoProximoBackup) {
        String sql = "SELECT programar_backup(?)";
        try (Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, novoProximoBackup);
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao programar backup", ex);
        }
    }

    public void realizarBackup() {
        String sql = "SELECT realizar_backup()";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao realizar backup", ex);
        }
    }

    public Timestamp checarBackup() {
        String sqlMaxId = "SELECT MAX(id) as id FROM backup_programado";
        String sqlProximoBackup = "SELECT proximo_backup FROM backup_programado WHERE id = ?";

        try (Connection connection = Conexao.getConnection()) {
            try (PreparedStatement statementMaxId = connection.prepareStatement(sqlMaxId)) {
                ResultSet resultadoMaxId = statementMaxId.executeQuery();
                if (resultadoMaxId.next()) {
                    int maxId = resultadoMaxId.getInt("id");

                    try (PreparedStatement statementProximoBackup = connection.prepareStatement(sqlProximoBackup)) {
                        statementProximoBackup.setInt(1, maxId);
                        ResultSet resultadoProximoBackup = statementProximoBackup.executeQuery();
                        if (resultadoProximoBackup.next()) {
                            return resultadoProximoBackup.getTimestamp("proximo_backup");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao obter o próximo backup", ex);
        }

        return null;
    }
}
