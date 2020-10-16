package edu.montana.csci.csci440.util;

import edu.montana.csci.csci440.model.Employee;
import org.sqlite.SQLiteConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;

public class DB {

    private static long CONNECTION_COUNT = 0;

    public static Connection connect() throws SQLException {
        CONNECTION_COUNT++;
        Connection connection = DriverManager.getConnection("jdbc:sqlite:db/chinook.db");
        try (Statement statement = connection.createStatement();){
            statement.execute("PRAGMA foreign_keys = ON");
        };
        return connection;
    }

    public static void reset() throws IOException {
        Path dbPath = Paths.get("db/chinook.db");
        Path backupPath = Paths.get("db/backup/original.db");
        if (Files.exists(dbPath)) {
            Files.copy(backupPath, dbPath, StandardCopyOption.REPLACE_EXISTING);
        } else {
            System.err.println("Could not find DB file!");
        }
    }

    public static long getLastID(Connection conn) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT last_insert_rowid() as ID")) {
            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                return results.getLong("ID");
            } else {
                throw new IllegalStateException("Could not get last ID");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public static long getConnectionCount() {
        return CONNECTION_COUNT;
    }
}
