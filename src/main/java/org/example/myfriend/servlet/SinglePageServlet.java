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

@WebServlet("/singlePage")
public class SinglePageServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final FriendService friendService = new FriendService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User currentUser = (User) req.getSession().getAttribute("user");
            if (currentUser == null) {
                resp.sendRedirect("/login");
                return;
            }

            String idParam = req.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing parameter 'id'");
                return;
            }

            int userId;
            try {
                userId = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter 'id' must be a valid number");
                return;
            }

            User searchingUser = userService.getUserById(userId);
            if (searchingUser == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                return;
            }


            Friend existingFriend = friendService.getFriendBySenderIdAndFriendId(currentUser.getId(), userId);
            Friend reverseFriendRequest = friendService.getFriendBySenderIdAndFriendId(userId, currentUser.getId());


            req.setAttribute("searchingUser", searchingUser);
            req.setAttribute("friend", existingFriend); // Если уже друзья
            req.setAttribute("friendRequest", reverseFriendRequest); // Если отправлен запрос дружбы
            req.getRequestDispatcher("/WEB-INF/singlePage.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }
    }
}
