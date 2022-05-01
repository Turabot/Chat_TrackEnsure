package com.example.finishchat.service;

import com.example.finishchat.dto.MessageDto;
import com.example.finishchat.dto.UserDto;
import com.example.finishchat.entity.Message;
import com.example.finishchat.entity.User;
import com.example.finishchat.mapper.MessageMapper;
import com.example.finishchat.mapper.UserMapper;
import com.example.finishchat.repository.MessageRepo;
import com.example.finishchat.repository.UserRepo;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MessageService {

    private static final MessageService INSTANCE = new MessageService();

    private final MessageRepo userRepo = MessageRepo.getInstance();
    private final MessageMapper createUserMapper = MessageMapper.getInstance();

    @SneakyThrows
    public void create(MessageDto messageDto) {

        System.out.println("+++++++++++ " + messageDto );

        Message message = createUserMapper.mapFrom(messageDto);
        userRepo.save(message);

//        return userEntity.getUsername();
    }

    public static MessageService getInstance() {
        return INSTANCE;
    }
}
