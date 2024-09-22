package io.hexlet.dao;

import io.hexlet.model.Song;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// BEGIN (write your solution here)
public class SongDAO {
    private Connection connection;

    public SongDAO(Connection conn) {
        connection = conn;
    }

    public List<Song> find(String word) throws SQLException {
        var listOfSongs = new LinkedList<Song>();
        var sql = "SELECT * FROM songs WHERE LOWER(title) LIKE ? ORDER BY title";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + word.toLowerCase() + "%");
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var title = resultSet.getString("title");
                var singerName = resultSet.getString("singer_name");

                var product = new Song(id, title, singerName);
                listOfSongs.add(product);
            }
            return listOfSongs;
        }
    }
}
// END
