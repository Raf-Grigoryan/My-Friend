package org.example.myfriend.servlet;

import org.example.myfriend.model.User;
import org.example.myfriend.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 5
)
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        StringBuilder builder = new StringBuilder();
        if (name == null || name.trim().length() < 3) {
            builder.append("Name must be at least 3 characters long and cannot be empty<br>");
        }
        if (surname == null || surname.trim().length() < 3) {
            builder.append("Surname must be at least 3 characters long and cannot be empty<br>");
        }
        if (email == null || email.trim().length() < 6) {
            builder.append("Email must be at least 6 characters long and cannot be empty<br>");
        }
        if (password == null || password.trim().length() < 6) {
            builder.append("Password must be at least 6 characters long and cannot be empty<br>");
        }

        if (builder.isEmpty()) {
            User user = userService.getUserByEmail(email);
            if (user == null) {
                user = User.builder()
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .password(password)
                        .imagePath("defualt.jpg")
                        .build();
                userService.addUser(user);
                resp.sendRedirect("/login");
            } else {
                builder.append("Email already in use<br>");
                req.getSession().setAttribute("registerMessage", builder.toString());
            }
        } else {
            req.getSession().setAttribute("registerMessage", builder.toString());
            resp.sendRedirect("/register");
        }

    }
}
