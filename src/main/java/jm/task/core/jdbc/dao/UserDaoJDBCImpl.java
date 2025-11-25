package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL" +
                ")";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            System.out.println("Таблица users успешно создана!");

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании таблицы: " + e.getMessage(), e);
        }
    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try(Connection connection = Util.getConnection();
        ) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);

                preparedStatement.executeUpdate();

                System.out.println("Пользователь с именем - " + name + " добавлен в базу данных");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return new ArrayList<User>();
    }

    public void cleanUsersTable() {

    }
}
