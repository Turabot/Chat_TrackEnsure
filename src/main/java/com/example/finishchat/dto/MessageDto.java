package com.example.finishchat.dto;

import com.example.finishchat.entity.User;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class MessageDto {

    String text;
    LocalDateTime createAt;
    User user;
}
