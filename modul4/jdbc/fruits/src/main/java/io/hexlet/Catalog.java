package io.hexlet;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import io.hexlet.dao.ProductDAO;

public class Catalog {
    // BEGIN (write your solution here)
    public static String getProduct(String title) throws SQLException {
        String result = "";
        var conn = DriverManager.getConnection("jdbc:h2:./hexlet");

        var productDAO = new ProductDAO(conn);

        var optional = productDAO.find(title);
        if (!optional.isEmpty()) {
            var product = optional.get();
            result = "Id: " + product.getId() +
                    "\nTitle: " + product.getTitle() +
                    "\nDescription: " + product.getDescription() +
                    "\nPrice: " + product.getPrice();
            return result;
        } else {
            throw new NoSuchElementException("Product " + title + " not found");
        }
    }
    // END
}
