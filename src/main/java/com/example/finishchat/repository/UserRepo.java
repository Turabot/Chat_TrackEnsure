package com.example.finishchat.repository;

import com.example.finishchat.entity.User;
import com.example.finishchat.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserRepo implements Repo<Integer, User> {

    private static final UserRepo INSTANCE = new UserRepo();

    private static final String SAVE_SQL = "INSERT INTO users (login) VALUES (?)";

    @Override
    @SneakyThrows
    public void save(User entity) {

        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {

            preparedStatement.setObject(1, entity.getUsername());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();

            entity.setId(generatedKeys.getObject("id", Integer.class));
        }
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
