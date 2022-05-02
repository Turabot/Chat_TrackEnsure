package com.example.finishchat.repository;

import com.example.finishchat.entity.Message;
import com.example.finishchat.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MessageRepo implements Repo<Long, Message> {

    private static final MessageRepo INSTANCE = new MessageRepo();

    private static final String SAVE_SQL = "INSERT INTO message (texts, create_at) VALUES (?, ?)";
    private static final String GET_MESSAGE_SQL = "SELECT texts FROM message ORDER BY id DESC LIMIT 50";

    @Override
    @SneakyThrows
    public Message save(Message entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {

            preparedStatement.setObject(1, entity.getText());
            preparedStatement.setObject(2, entity.getCreateAt());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Long.class));
            return entity;


        }
    }

    @SneakyThrows
    public List<String> findAllMessage() {
        List<String> messages = new ArrayList<>();

        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MESSAGE_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                messages.add(resultSet.getString("texts"));
            }
        }
        return messages;
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public Optional<Message> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Message entity) {

    }

    public static MessageRepo getInstance() {
        return INSTANCE;
    }
}
