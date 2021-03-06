package com.example.finishchat.service;

import com.example.finishchat.dto.UserDto;
import com.example.finishchat.entity.User;
import com.example.finishchat.mapper.UserMapper;
import com.example.finishchat.repository.UserRepo;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserRepo userRepo = UserRepo.getInstance();
    private final UserMapper createUserMapper = UserMapper.getInstance();

    @SneakyThrows
    public void create(UserDto userDto) {
        User userEntity = createUserMapper.mapFrom(userDto);
        userRepo.save(userEntity);
    }

    public User getByNickName(String userName) {
        return userRepo.findUserByUserName(userName);
    }

    public Boolean existByNickName(String nickName) {
        return userRepo.existByUserName(nickName);
    }


    public static UserService getInstance() {
        return INSTANCE;
    }
}
