package io.hexlet;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import io.hexlet.dao.CarDAO;

public class Base {
    // BEGIN (write your solution here)
    public static String getInfo(String number) throws SQLException {
        String result = "";
        var conn = DriverManager.getConnection("jdbc:h2:./hexlet");

        var carDAO = new CarDAO(conn);

        var optional=carDAO.find(number);
        if (!optional.isEmpty()) {
            var car = optional.get();
            result = "Car:" +
                    "\nMade: " + car.getMade() +
                    "\nModel: " + car.getModel() +
                    "\nColor: " + car.getColor() +
                    "\nBelong to:" +
                    "\n" + car.getOwner().getFirst_name() + " " + car.getOwner().getSecond_name() +
                    "\nAddress: " + car.getOwner().getAdress();
            return result;
        } else {
            throw new NoSuchElementException("no found car!");
        }
    }
    // END
}
