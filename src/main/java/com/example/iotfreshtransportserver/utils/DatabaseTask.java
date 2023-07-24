package com.example.iotfreshtransportserver.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DatabaseTask {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    // 每天凌晨1点执行备份操作
    @Scheduled(cron = "0 0 1 * * ?")
    public void backupDatabase() {
        String backupFilePath = "backup.sql";
        String sql = "mysqldump --user=" + databaseUsername +
                " --password=" + databasePassword +
                " --databases " + databaseUrl +
                " > " + backupFilePath;

        Process process;
        try {
            process = Runtime.getRuntime().exec(sql);
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                System.out.println("Database backup success. Backup file: " + backupFilePath);
            } else {
                System.out.println("Database backup failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 每周日凌晨2点执行恢复操作
    @Scheduled(cron = "0 0 2 ? * SUN")
    public void restoreDatabase() {
        String restoreFilePath = "backup.sql";
        String sql = "mysql --user=" + databaseUsername +
                " --password=" + databasePassword +
                " " + databaseUrl +
                " < " + restoreFilePath;

        Process process;
        try {
            process = Runtime.getRuntime().exec(sql);
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                System.out.println("Database restore success.");
            } else {
                System.out.println("Database restore failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
