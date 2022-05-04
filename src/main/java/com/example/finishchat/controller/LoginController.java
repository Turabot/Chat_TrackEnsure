package com.example.finishchat.controller;

import com.example.finishchat.dto.UserDto;
import com.example.finishchat.entity.User;
import com.example.finishchat.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = req.getParameter("name");

        UserDto userDto = UserDto.builder()
                .username(name)
                .build();

        if (userService.existByNickName(name)) {
            userService.create(userDto);
        }

        User user = userService.getByNickName(userDto.getUsername());

        HttpSession session = req.getSession();

        session.setAttribute("users", user);
        session.setAttribute("userName", userDto.getUsername());

        resp.sendRedirect("/message");
    }

}


