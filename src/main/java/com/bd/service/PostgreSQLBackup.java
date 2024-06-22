package com.bd.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLBackup {
    
    public boolean realizarBackup(String host, String port, String username, String database, String password) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        String outputPath = "C:\\Users\\Camil\\Desktop\\Faculdade 2024\\Banco de Dados II\\Trabalho Prático II\\Projeto_Banco_II\\sql\\Backup\\backup_" + formattedDateTime + ".sql";

        try {
            List<String> command = new ArrayList<>();
            command.add("C:\\Program Files\\PostgreSQL\\14\\bin\\pg_dump.exe");
            command.add("-h");
            command.add(host);
            command.add("-p");
            command.add(port);
            command.add("-U");
            command.add(username);
            command.add("-d");
            command.add(database);
            command.add("-f");
            command.add(outputPath);

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.environment().put("PGPASSWORD", password);

            Process process = pb.start();

            int exitCode = process.waitFor();
            
            if (exitCode == 0) {               
                return true;
            } else {
                return false;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
