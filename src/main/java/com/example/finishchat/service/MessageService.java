package com.example.finishchat.service;

import com.example.finishchat.dto.MessageDto;
import com.example.finishchat.entity.Message;
import com.example.finishchat.mapper.MessageMapper;
import com.example.finishchat.repository.MessageRepo;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MessageService {

    private static final MessageService INSTANCE = new MessageService();

    private final MessageRepo userRepo = MessageRepo.getInstance();
    private final MessageMapper createUserMapper = MessageMapper.getInstance();

    public void create(MessageDto messageDto) {
        Message message = createUserMapper.mapFrom(messageDto);
        userRepo.save(message);

//        return userEntity.getUsername();
    }

    public List<String> getMessages() {
        List<String> allMessage = userRepo.findAllMessage();
        Collections.reverse(allMessage);
        return allMessage;
    }

    public static MessageService getInstance() {
        return INSTANCE;
    }
}
