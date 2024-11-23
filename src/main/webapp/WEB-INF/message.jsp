<%@ page import="org.example.myfriend.model.Friend" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.myfriend.model.User" %>
<%@ page import="org.example.myfriend.model.Message" %><%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 23.11.24
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Message</title>
    <link rel="stylesheet" href="../css/message.css">
</head>
<body>
<div class="container">
    <div class="friends-list">
        <h3>Your Friends</h3>
        <ul>
            <%  User currentUser = (User) session.getAttribute("user");
                List<Friend> friends = (List<Friend>) request.getAttribute("friends");
                for (Friend friend : friends) {
                    User friendUser = friend.getFriend().getId() != currentUser.getId() ? friend.getFriend() : friend.getSender();
            %>
            <li>
                <a href="/sendMessage?id=<%= friendUser.getId() %>">
                    <span><%= friendUser.getName() %> <%= friendUser.getSurname() %></span>
                </a>
            </li>
            <% } %>
        </ul>
    </div>


    <div class="chat-window">
        <div class="messages">
            <%
                List<Message> messages = (List<Message>) request.getAttribute("messages");
                for (Message message : messages) {
            %>
            <div class="message">
                <div class="message-sender"><%= message.getSender().getName() %>:</div>
                <div class="message-text"><%= message.getMessage() %></div>
            </div>
            <% } %>
        </div>
        <form action="/sendMessage" method="POST">
            <input type="hidden" name="friendId" value="<%= request.getAttribute("friendId") %>">
            <input type="text" name="message" placeholder="Type a message..." required>
            <button type="submit">Send</button>
        </form>
    </div>
</div>
</body>
</html>