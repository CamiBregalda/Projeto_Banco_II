package com.bd.repository;


import com.bd.infra.Conexao;
import com.bd.model.Usuario;
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
}
