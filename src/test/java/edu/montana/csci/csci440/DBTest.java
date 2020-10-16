package edu.montana.csci.csci440;

import edu.montana.csci.csci440.util.DB;
import org.junit.jupiter.api.AfterEach;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DBTest {
    @AfterEach
    public void resetDB() {
        try {
            System.out.println("Resetting DB After Test...");
            DB.reset();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean executeDDL(String ddl) {
        try (Connection conn = DB.connect();
             Statement stmt = conn.createStatement()) {
            return stmt.execute(ddl);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public List<Map<String, Object>> executeSQL(String sqlString) {
        try (Connection conn = DB.connect();
             Statement stmt = conn.createStatement()) {
            ResultSet results = stmt.executeQuery(sqlString);
            List<Map<String, Object>> resultList = new LinkedList<>();
            while (results.next()) {
                ResultSetMetaData metaData = results.getMetaData();
                int columns = metaData.getColumnCount();
                Map<String, Object> hashMap = new HashMap<>();
                for (int i = 0; i < columns; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    hashMap.put(columnName, results.getObject(i + 1));
                }
                resultList.add(hashMap);
            }
            return resultList;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public boolean executeUpdate(String sqlString) {
        try (Connection conn = DB.connect();
             Statement stmt = conn.createStatement()) {
            return stmt.execute(sqlString);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }
}
