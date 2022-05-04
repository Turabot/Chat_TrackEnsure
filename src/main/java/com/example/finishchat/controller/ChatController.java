package com.example.finishchat.controller;

import com.example.finishchat.dto.MessageDto;
import com.example.finishchat.entity.Message;
import com.example.finishchat.entity.User;
import com.example.finishchat.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/message")
public class ChatController extends HttpServlet {

    private final MessageService messageService = MessageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Message> andUserName = messageService.getMessagesAndUserName();
        req.setAttribute("messages", andUserName);
        req.getRequestDispatcher("/WEB-INF/message.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("users");

        MessageDto build = MessageDto.builder()
                .text(req.getParameter("txtMsg"))
                .user(user)
                .build();

        messageService.create(build);

        doGet(req, resp);
    }
}
