package com.example.finishchat.dto;

import com.example.finishchat.entity.Message;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class UserDto {

    String username;
    LocalDateTime createAt;
    List<Message> messages;
}
