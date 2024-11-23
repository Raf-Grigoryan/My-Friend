package org.example.myfriend.servlet;

import org.example.myfriend.model.User;
import org.example.myfriend.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String username = req.getParameter("username");
        List<User> users = userService.searchUser(username, user.getId());
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/searchUsers.jsp").forward(req, resp);
    }
}
