package edu.montana.csci.csci440.model;

import edu.montana.csci.csci440.util.DB;
import org.eclipse.jetty.http.MetaData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MediaType extends Model {

    private Long mediaTypeId;
    private String name;

    private MediaType(ResultSet results) throws SQLException {
        name = results.getString("Name");
        mediaTypeId = results.getLong("MediaTypeId");
    }

    public Long getMediaTypeId() {
        return mediaTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<MediaType> all() {
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM media_types"
             )) {
            ResultSet results = stmt.executeQuery();
            List<MediaType> resultList = new LinkedList<>();
            while (results.next()) {
                resultList.add(new MediaType(results));
            }
            return resultList;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

}
