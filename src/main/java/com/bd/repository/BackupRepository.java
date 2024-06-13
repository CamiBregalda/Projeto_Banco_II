package com.bd.repository;


import com.bd.infra.Conexao;
import com.bd.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BackupRepository {
/*
    public Usuario realizarBackup (Long id) {
        try (Connection connection = Conexao.getConnection()) {
            String backupFilePath = "C:/Users/Camil/Desktop/Faculdade 2024/Banco de Dados II/Trabalho Prático II/Projeto_Banco_II/sql/backup_loja_virtual_" + System.currentTimeMillis() + ".bak";
            String sql = "BACKUP DATABASE loja_virtual TO DISK = '" + backupFilePath + "'";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute(sql);
                statement.close();
                System.out.println("Backup do banco de dados criado com sucesso!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar usuário", ex);
        }
    }*/
}
