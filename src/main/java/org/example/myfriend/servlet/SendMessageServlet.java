package org.example.myfriend.servlet;

import org.example.myfriend.model.Message;
import org.example.myfriend.model.User;
import org.example.myfriend.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/sendMessage")
public class SendMessageServlet extends HttpServlet {
    private final MessageService messageService = new MessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = ((User) req.getSession().getAttribute("user")).getId();
        int friendId = Integer.parseInt(req.getParameter("id"));


        List<Message> messages = messageService.getMessagesBySenderAndFriend(userId, friendId);


        req.setAttribute("messages", messages);
        req.setAttribute("friendId", friendId);
        req.getRequestDispatcher("/WEB-INF/sendMessage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = ((User) req.getSession().getAttribute("user")).getId();
        int friendId = Integer.parseInt(req.getParameter("friendId"));
        String messageText = req.getParameter("message");
        Date date = new Date();


        Message message = Message.builder()
                .sender(User.builder().id(userId).build())
                .friend(User.builder().id(friendId).build())
                .message(messageText)
                .date(date)
                .build();

        messageService.sendMessage(message);
        resp.sendRedirect("/sendMessage?id=" + friendId);
    }
}

