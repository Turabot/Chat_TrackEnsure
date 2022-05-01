package com.example.finishchat.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
