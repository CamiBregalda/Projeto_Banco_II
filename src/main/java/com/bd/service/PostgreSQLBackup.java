package com.bd.service;

import com.bd.exception.BusinessException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLBackup {
/*
    public static void main(String[] args) {
        realizarBackup();

        /*
        // Defina o caminho personalizado para o arquivo .pgpass
        String outputPath = "C:\\Users\\Camil\\Desktop\\Faculdade 2024\\Banco de Dados II\\Trabalho Prático II\\Projeto_Banco_II\\sql\\Backup\\backup.sql";

        // Define a variável de ambiente PGPASSFILE
        System.setProperty("PGPASSFILE", pgpassFile);

        // Exemplo de execução do comando pg_dump
        try {
            String[] command = {
                    "C:\\Program Files\\PostgreSQL\\14\\bin\\pg_dump.exe",
                    "-h", "localhost",
                    "-p", "5432",
                    "-U", "postgres",
                    "-d", "loja_virtual",
                    "-f", outputPath
            };

            // Executa o comando pg_dump
            Process process = Runtime.getRuntime().exec(command);
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Backup realizado com sucesso.");
            } else {
                System.err.println("Erro ao realizar o backup.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        String host = "localhost"; // Host onde o PostgreSQL está rodando
        String port = "5432"; // Porta do PostgreSQL
        String username = "postgres"; // Usuário do PostgreSQL
        String database = "loja_virtual"; // Nome do banco que deseja fazer backup
        String password = "root";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        String outputPath = "C:\\Users\\Camil\\Desktop\\Faculdade 2024\\Banco de Dados II\\Trabalho Prático II\\Projeto_Banco_II\\sql\\Backup\\backup_" + formattedDateTime + ".sql"; // Caminho completo para o arquivo de backup

        try {
            // Montando o comando pg_dump
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

            // Criando o processo
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.environment().put("PGPASSWORD", password);

            // Iniciando o processo
            Process process = pb.start();

            // Esperando o término do processo
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
