package com.example.finishchat.mapper;

import com.example.finishchat.dto.MessageDto;
import com.example.finishchat.entity.Message;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MessageMapper implements Mapper<MessageDto, Message> {

    private static final MessageMapper INSTANCE = new MessageMapper();

    @Override
    public Message mapFrom(MessageDto object) {
        return Message.builder()
                .text(object.getText())
                .user(object.getUser())
                .build();
    }

    public static MessageMapper getInstance() {
        return INSTANCE;
    }
}
