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

@WebServlet("/addFriend")
public class AddFriendServlet extends HttpServlet {

    private final FriendService friendService = new FriendService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("user");
        Friend friend = friendService.getFriendBySenderIdAndFriendId(id, user.getId());
        if (friend != null) {
            friendService.acceptFriend(id, user.getId());
        }
        req.getRequestDispatcher("/friend").forward(req, resp);
    }
}
