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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/friend")
public class FriendServlet extends HttpServlet {
    private final FriendService friendService = new FriendService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        List<Friend> allFriends = friendService.getFriendsBySenderId(currentUser.getId());

        List<Friend> filteredFriends = new ArrayList<>();
        for (Friend friend : allFriends) {
            if (friend.getSender().getId() == currentUser.getId() && friend.getFriend().getId() != currentUser.getId()) {
                filteredFriends.add(friend);
            } else if (friend.getFriend().getId() == currentUser.getId() && friend.getSender().getId() != currentUser.getId()) {
                filteredFriends.add(friend);
            }
        }

        req.setAttribute("friends", filteredFriends);
        req.getRequestDispatcher("/WEB-INF/friends.jsp").forward(req, resp);
    }
}
