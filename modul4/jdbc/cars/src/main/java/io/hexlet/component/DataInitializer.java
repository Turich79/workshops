package io.hexlet.component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;

public class DataInitializer {
    public static void init(Connection connection) throws Exception {

        var path = Paths.get("src/main/java/io/hexlet/component/init.sql");
        var sql = Files.readString(path);

        try (var statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
}
