package com.example.finishchat.controller;

import com.example.finishchat.dto.UserDto;
import com.example.finishchat.service.MessageService;
import com.example.finishchat.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "userLogin", urlPatterns = {"/userLogin"})
public class LoginController extends HttpServlet {

    private final UserService userService = UserService.getInstance();
    private final MessageService messageService = MessageService.getInstance();

    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("user");

        PrintWriter out = response.getWriter();

        try {
            session = request.getSession();
            session.setAttribute("username", request.getParameter("user"));
            String username = session.getAttribute("username").toString();


            out.println("<html>  <head> <body bgcolor=\"#6495ED\"> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> <title>Chat Room</title>  </head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> <center>");
            out.println("<h2>Hi ");
            out.println(username);
            out.println("<br> Welcome to Hello Chat ");
            out.println("</h2><br><hr>");
            out.println("  <body>");
            out.println("      <form name=\"chatWindow\" action=\"chatWindow\">");
            out.println("Message: <input type=\"text\" name=\"txtMsg\" value=\"\" /><input type=\"submit\" value=\"Send\" name=\"cmdSend\"/>");
            out.println("<br><br> <a href=\"chatWindow\">Refresh Chat Room</a>");
            out.println("<br>  <br>");
            out.println("Messages in Chat Box:");
            out.println("<br><br>");
            out.println("<textarea  readonly=\"readonly\"   name=\"txtMessage\" rows=\"20\" cols=\"60\">");

                List<String> messages = messageService.getMessages();

                for (String message : messages) {
                    out.println(message);
                }

            out.println("</textarea>");
            out.println("<hr>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");


        } catch (Exception e) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Sevlet failed</h1>");
            out.println("</body>");
            out.println("</html>");
            System.out.println(e);
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");

        UserDto build = UserDto.builder().name(user).build();
        userService.create(build);
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
