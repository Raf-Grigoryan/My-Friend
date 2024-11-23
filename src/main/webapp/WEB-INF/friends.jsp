<%@ page import="org.example.myfriend.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.myfriend.model.Friend" %><%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 22.11.24
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Friends</title>
    <link rel="stylesheet" href="../css/friend.css">
</head>
<body>

<header>
    <h1>My Friends</h1>
    <nav>
        <a href="/home">Home</a>
        <a href="/profile">Profile</a>
    </nav>
</header>

<div class="container">
    <div class="friend-list">
        <% User currentUser = (User) session.getAttribute("user");
            List<Friend> friends = (List<Friend>) request.getAttribute("friends");
            if (friends != null && !friends.isEmpty()) {
                for (Friend friend : friends) {
                    User friendUser = (friend.getSender().getId() != currentUser.getId()) ? friend.getSender() : friend.getFriend();
        %>
        <div class="friend-item">
            <div class="friend-avatar">
                <img src="/getImage?imagName=<%=friendUser.getImagePath()%>">
            </div>
            <div class="friend-info">
                <p><strong>Name:</strong> <%= friendUser.getName() %> <%= friendUser.getSurname() %>
                </p>
                <p><strong>Email:</strong> <%= friendUser.getEmail() %>
                </p>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <p>You have no friends yet.</p>
        <% } %>
    </div>
</div>

</body>
</html>