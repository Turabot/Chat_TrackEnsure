package com.example.finishchat.repository;

import com.example.finishchat.entity.User;
import com.example.finishchat.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserRepo implements Repo<Integer, User> {

    private static final UserRepo INSTANCE = new UserRepo();

    private static final String SAVE_SQL = "INSERT INTO users (user_name, create_at) VALUES (?, ?)";

    private static final String FIND_ID_USER_NAME = "SELECT user_id, user_name, create_at FROM users WHERE user_name = ?";

    private static final String EXIST_BY_USER_NAME = "SELECT COUNT(*) FROM users WHERE user_name = ?";

    protected User resultSetBuilder(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("user_id"))
                .username(resultSet.getString("user_name"))
                .build();
    }

    @Override
    @SneakyThrows
    public void save(User entity) {

        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {

            preparedStatement.setObject(1, entity.getUsername());
            preparedStatement.setObject(2, LocalDateTime.now());

            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public User findUserByUserName(String userName) {

        User user = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ID_USER_NAME)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = resultSetBuilder(resultSet);
            }
        }
        return user;
    }

    public Boolean existByUserName(String userName) {
        boolean isUserName = false;

        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(EXIST_BY_USER_NAME)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isUserName = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUserName;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    public static UserRepo getInstance() {
        return INSTANCE;
    }
}
