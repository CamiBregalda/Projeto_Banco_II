package com.bd.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BackupService {

    public boolean realizarBackup(String host, String port, String username, String database, String password) {
        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String outputPath = "C:\\Backup\\backup_" + formattedDateTime + ".sql";

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

        try {
            Process process = pb.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }
}
