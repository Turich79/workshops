package io.hexlet;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import io.hexlet.dao.SongDAO;
import io.hexlet.model.Song;

public class MusicService {
    // BEGIN (write your solution here)
    public static List<Song> getSongByTitle(String word)  throws SQLException {
        var conn = DriverManager.getConnection("jdbc:h2:./hexlet");
        var songDAO = new SongDAO(conn);

        return songDAO.find(word);
    }
    // END
}
