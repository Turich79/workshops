package io.hexlet.dao;

import io.hexlet.model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

// BEGIN (write your solution here)
public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection conn) {
        connection = conn;
    }

    public Optional<Product> find(String number) throws SQLException {
        var sql = "SELECT * FROM products WHERE title = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, number);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var id = resultSet.getLong("id");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var price = resultSet.getInt("price");

                var product = new Product(id, title, description, price);
                return Optional.of(product);
            }
            return Optional.empty();
        }
    }
}
// END
