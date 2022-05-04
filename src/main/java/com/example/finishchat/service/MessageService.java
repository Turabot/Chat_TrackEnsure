package com.example.finishchat.service;

import com.example.finishchat.dto.MessageDto;
import com.example.finishchat.entity.Message;
import com.example.finishchat.mapper.MessageMapper;
import com.example.finishchat.repository.MessageRepo;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MessageService {

    private static final MessageService INSTANCE = new MessageService();

    private final MessageRepo messageRepo = MessageRepo.getInstance();
    private final MessageMapper createUserMapper = MessageMapper.getInstance();

    public void create(MessageDto messageDto) {
        Message message = createUserMapper.mapFrom(messageDto);
        messageRepo.save(message);
    }

    public List<Message> getMessagesAndUserName() {
        List<Message> allMessageTo = messageRepo.findAllMessageTo();
        Collections.reverse(allMessageTo);
        return allMessageTo;
    }

    public static MessageService getInstance() {
        return INSTANCE;
    }
}
