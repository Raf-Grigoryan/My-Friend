package org.example.myfriend.servlet;

import org.example.myfriend.model.Friend;
import org.example.myfriend.model.User;
import org.example.myfriend.service.FriendService;
import org.example.myfriend.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sendRequestFriend")
public class SendFriendRequest extends HttpServlet {
    private final FriendService friendService = new FriendService();
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("user");
        if (user.getId() != id) {
            Friend friend = friendService.getFriendBySenderIdAndFriendId(user.getId(), id);
            if (friend == null) {
                friend = Friend.builder()
                        .sender(user)
                        .friend(userService.getUserById(id))
                        .build();
                friendService.sendRequestFriend(friend);
                resp.sendRedirect("/search");
            } else {
                req.getRequestDispatcher("/home").forward(req, resp);
            }
        }
    }
}
