package com.example;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Show the login form (GET request)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Applicant Login</h2>");
        out.println("<form method='POST' action='login'>");
        out.println("Username: <input type='text' name='username'><br><br>");
        out.println("Email: <input type='email' name='email'><br><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    // Handle form submission (POST request)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Login Successful</h2>");
        out.println("<p>Welcome, <b>" + username + "</b></p>");
        out.println("<p>Your email: <b>" + email + "</b></p>");
        out.println("</body></html>");
    }
}
