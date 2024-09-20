package io.hexlet;

import io.hexlet.component.DataInitializer;
import java.sql.DriverManager;

public class App {
    public static void main(String[] args) throws Exception {

        try (var conn = DriverManager.getConnection("jdbc:h2:./hexlet")) {
            DataInitializer.init(conn);
        }

        for (var arg : args) {
            try {
                System.out.println(Base.getInfo(arg));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
