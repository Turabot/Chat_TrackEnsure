package com.example.finishchat.controller;

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
@WebServlet(name = "chatWindow", urlPatterns = {"/chatWindow"})
public class ChatController extends HttpServlet {

    String username, tempName;
    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()) {

            String message = request.getParameter("txtMsg");  //Extract Message
            String username = session.getAttribute("username").toString(); //Extract Username


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

            if (request.getParameter("txtMsg") != null) {

                try(Connection connection = ConnectionManager.get();
                    Statement st = connection.createStatement()) {

                    String sql = "INSERT INTO hello_message VALUES ('" + username + "','" + message + "')";
                    st.executeUpdate(sql);

                    st.execute("commit");

                } catch (Exception ex1) {
                    System.err.println(ex1.getMessage());
                    String messages = "No";
                    out.println(messages);
                }
            }
            // Retrieve all messages from database

            try(Connection connection = ConnectionManager.get();
                Statement st = connection.createStatement()) {

                ResultSet rs = st.executeQuery("SELECT * FROM hello_message");

                // Print all retrieved messages
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
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();

        if (username != null) {
            tempName = username;
        }

        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("txtMsg");
        System.out.println(message + " This you message");
        super.doPost(req, resp);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
