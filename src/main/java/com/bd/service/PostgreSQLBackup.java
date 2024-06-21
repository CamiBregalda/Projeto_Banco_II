package com.bd.service;

import com.bd.exception.BusinessException;
import com.bd.infra.Login;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLBackup {

    public void realizarBackup(String host, String port, String username, String database, String password) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        String outputPath = "C:\\Backup\\backup_" + formattedDateTime + ".sql";

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
                System.out.println("Backup realizado com sucesso em: " + outputPath);
            } else {
                System.out.println("Erro ao realizar o backup. Código de saída: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
