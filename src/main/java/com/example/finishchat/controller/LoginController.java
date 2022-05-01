package com.example.finishchat.controller;

import com.example.finishchat.dto.UserDto;
import com.example.finishchat.service.UserService;
import com.example.finishchat.util.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "userLogin", urlPatterns = {"/userLogin"})
public class LoginController extends HttpServlet {

    private final UserService userService = UserService.getInstance();

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


            // Retrieve all messages from database

            try(Connection connection = ConnectionManager.get();
                Statement st = connection.createStatement()) {

                ResultSet rs = st.executeQuery("SELECT * FROM hello_message");

                while (rs.next()) {
                    String messages = rs.getString(1) + " >> " + rs.getString(2);
                    out.println(messages);
                }

            } catch (Exception ex1) {
                System.err.println(ex1.getMessage());
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


//        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");

        UserDto build = UserDto.builder().name(user).build();
        userService.create(build);
        System.out.println(user + " This USer you");
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
