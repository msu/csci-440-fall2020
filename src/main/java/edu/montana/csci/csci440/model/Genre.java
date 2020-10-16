package edu.montana.csci.csci440.model;

import edu.montana.csci.csci440.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Genre extends Model {

    private Long genreId;
    private String name;

    private Genre(ResultSet results) throws SQLException {
        name = results.getString("Name");
        genreId = results.getLong("GenreId");
    }

    public Long getGenreId() {
        return genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Genre> all() {
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM genres"
             )) {
            ResultSet results = stmt.executeQuery();
            List<Genre> resultList = new LinkedList<>();
            while (results.next()) {
                resultList.add(new Genre(results));
            }
            return resultList;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }


}
