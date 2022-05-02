package com.example.finishchat.controller;

import com.example.finishchat.dto.MessageDto;
import com.example.finishchat.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/message")
public class ChatController extends HttpServlet {

    private final MessageService messageService = MessageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/message.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("txtMsg");

        MessageDto build = MessageDto.builder()
                .text(message)
                .build();
        messageService.create(build);

        PrintWriter writer = resp.getWriter();
        List<String> messages = messageService.getMessages();
        for (String s : messages) {
            System.out.println(s);
            writer.println(s);
        }
        doGet(req, resp);
    }
}
