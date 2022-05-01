package com.example.finishchat.mapper;

import com.example.finishchat.dto.UserDto;
import com.example.finishchat.entity.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserMapper implements Mapper<UserDto, User> {

    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public User mapFrom(UserDto object) {
        return User.builder().
                username(object.getName())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
