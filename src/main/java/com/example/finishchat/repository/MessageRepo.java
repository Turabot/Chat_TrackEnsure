package com.example.finishchat.repository;

import com.example.finishchat.entity.Message;
import com.example.finishchat.entity.User;
import com.example.finishchat.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MessageRepo implements Repo<Long, Message> {

    private static final MessageRepo INSTANCE = new MessageRepo();

    private static final String SAVE_SQL = "INSERT INTO messages (text, create_at, user_id) VALUES (?, ?, ?)";
    private static final String GET_50_MESSAGE_SQL = "SELECT * FROM messages  JOIN users u on u.user_id = messages.user_id ORDER BY message_id DESC LIMIT 50 ";

    protected Message resultSetBuilder(ResultSet resultSet) throws SQLException {
        return Message.builder()
                .id(resultSet.getLong("user_id"))
                .text(resultSet.getString("text"))
                .user(User.builder()
                        .username(resultSet.getString("user_name"))
                        .build())
                .build();
    }

    @Override
    @SneakyThrows
    public void save(Message message) {

        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {

            preparedStatement.setObject(1, message.getText());
            preparedStatement.setObject(2, LocalDateTime.now());
            preparedStatement.setObject(3, message.getUser().getId());

            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public List<Message> findAllMessageTo() {
        List<Message> messages = new ArrayList<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_50_MESSAGE_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                messages.add(resultSetBuilder(resultSet));
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
