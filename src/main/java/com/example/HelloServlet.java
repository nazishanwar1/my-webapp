package com.example;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {

    // Show login page or welcome page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            String user = (String) session.getAttribute("username");
            out.println("<h1>Hello! Welcome to My-WebApp, " + user + "</h1>");
            out.println("<p>Email: " + session.getAttribute("email") + "</p>");
            out.println("<a href='HelloServlet?action=logout'>Logout</a>");
        } else {
            out.println("<h2>Login Page</h2>");
            out.println("<form method='post' action='HelloServlet'>");
            out.println("Username: <input type='text' name='username' required><br>");
            out.println("Email: <input type='email' name='email' required><br>");
            out.println("<input type='submit' value='Login'>");
            out.println("</form>");
        }
    }

    // Handle login + logout
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("email", email);

        response.sendRedirect("HelloServlet"); // redirect to GET
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, HttpSession session)
            throws IOException, ServletException {
        String action = req.getParameter("action");
        if ("logout".equals(action)) {
            HttpSession s = req.getSession(false);
            if (s != null) s.invalidate();
            resp.sendRedirect("HelloServlet");
        } else {
            super.doGet(req, resp);
        }
    }
}
