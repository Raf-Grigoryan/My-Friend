package org.example.myfriend.servlet;


import org.example.myfriend.model.Friend;
import org.example.myfriend.model.User;
import org.example.myfriend.service.FriendService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {

    private final FriendService friendService = new FriendService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Friend> friends = friendService.getFriendRequests(user.getId());
        req.setAttribute("friends", friends);
        req.getRequestDispatcher("/WEB-INF/requests.jsp").forward(req, resp);
    }
}
