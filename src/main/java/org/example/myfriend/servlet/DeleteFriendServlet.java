package org.example.myfriend.servlet;

import org.example.myfriend.model.User;
import org.example.myfriend.service.FriendService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteFriend")
public class DeleteFriendServlet extends HttpServlet {

    private final FriendService friendService = new FriendService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("id"));
        if (friendService.getFriendBySenderIdAndFriendId(user.getId(), id) != null) {
            friendService.removeFriendBySenderIdAndFriendId(user.getId(), id);
        }
        req.getRequestDispatcher("/friends.jsp").forward(req, resp);
    }
}
