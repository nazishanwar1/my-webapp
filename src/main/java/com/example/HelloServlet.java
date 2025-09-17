package com.example;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Store all registered users in memory (for demo)
    private static List<String> users = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (session != null && session.getAttribute("username") != null) {
            // Show welcome page
            String user = (String) session.getAttribute("username");
            out.println("<h1>Hello! Welcome to My-WebApp, " + user + "</h1>");
            out.println("<a href='HelloServlet?action=logout'>Logout</a><br><br>");
            out.println("<a href='HelloServlet?action=users'>View All Applicants</a>");
        } else {
            // Show login form
            out.println("<h2>Login Page</h2>");
            out.println("<form method='post' action='HelloServlet'>");
            out.println("Username: <input type='text' name='username' required><br>");
            out.println("Email: <input type='email' name='email' required><br>");
            out.println("<input type='submit' value='Login'>");
            out.println("</form>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String action = request.getParameter("action");

        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) session.invalidate();
            response.sendRedirect("HelloServlet");
            return;
        }

        // Handle login
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        if (username != null && !username.isEmpty() && email != null && !email.isEmpty()) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("email", email);

            // Save user for demo purpose
            users.add(username + " (" + email + ")");
            response.sendRedirect("HelloServlet");
        } else {
            response.getWriter().println("<h3>Error: Username and Email required!</h3>");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().println("PUT not supported.");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().println("DELETE not supported.");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if ("users".equals(request.getParameter("action"))) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Registered Applicants</h2>");
            for (String u : users) {
                out.println("<p>" + u + "</p>");
            }
            out.println("<a href='HelloServlet'>Back</a>");
        } else {
            super.service(request, response);
        }
    }
}
