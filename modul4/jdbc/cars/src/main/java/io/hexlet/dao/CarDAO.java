package io.hexlet.dao;

import io.hexlet.model.Car;
import io.hexlet.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class CarDAO {

    private Connection connection;

    public CarDAO(Connection conn) {
        connection = conn;
    }

    public void save(Car car) throws SQLException {
        if (car.getId() == null) {
            var sql = "INSERT INTO cars (made, model, number, color, owner_id) VALUES (?, ?, ?, ?, ?)";
            try (var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, car.getMade());
                preparedStatement.setString(2, car.getModel());
                preparedStatement.setString(3, car.getNumber());
                preparedStatement.setString(4, car.getColor());
                preparedStatement.setLong(5, car.getOwner().getId());
                preparedStatement.executeUpdate();
                var generatedKeys = preparedStatement.getGeneratedKeys();
                // Если идентификатор сгенерирован, извлекаем его и добавляем в сохраненный объект
                if (generatedKeys.next()) {
                    // Обязательно устанавливаем id в сохраненный объект
                    car.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("DB have not returned an id after saving an entity");
                }
            }
        }
    }

    public void del(Car car) throws SQLException {
        if (car.getId() != null) {
            var sql = "DELETE FROM cars WHERE id = ?";
            try (var predStatement = connection.prepareStatement(sql)) {
                predStatement.setLong(1, car.getId());
                predStatement.executeUpdate();
            }
        }
    }

    public Optional<Car> find(String number) throws SQLException {
        var sql = "SELECT " +
                "cars.id AS id," +
                "cars.made AS made," +
                "cars.model AS model," +
                "cars.color AS color," +
                "users.id AS owner_id," +
                "users.first_name AS first_name," +
                "users.last_name AS last_name," +
                "users.adress AS adress" +
                " FROM cars INNER JOIN users ON cars.user_id=users.id" +
                "WHERE number = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, number);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var id = resultSet.getLong("id");
                var made = resultSet.getString("made");
                var model = resultSet.getString("model");
                var color = resultSet.getString("color");
                var owner_id = resultSet.getLong("owner_id");
                var first_name = resultSet.getString("first_name");
                var last_name = resultSet.getString("last_name");
                var adress = resultSet.getString("adress");
                var user = new User(first_name, last_name, adress);
                user.setId(owner_id);
                var car = new Car(made, model, number, color);
                car.setOwner(user);
                car.setId(id);
                return Optional.of(car);
            }
            return Optional.empty();
        }
    }
}
