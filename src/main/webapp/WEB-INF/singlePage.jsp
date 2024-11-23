<%@ page import="org.example.myfriend.model.User" %>
<%@ page import="org.example.myfriend.model.Friend" %><%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 22.11.24
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="../css/singlePage.css">
</head>
<body>

<header>
    <h1>Welcome to Your Profile</h1>
</header>

<nav>
    <a href="/profile">My Profile</a>
    <a href="/edit-profile">Edit Profile</a>
    <a href="/requests">Requests</a>
    <a href="/friends">Friends</a>
    <a href="/messages">Messages</a>
</nav>

<% Friend friendRequest = (Friend) request.getAttribute("friendRequest");
    User searchingUser = (User) request.getAttribute("searchingUser");
    Friend friend = (Friend) request.getAttribute("friend");
%>

<div class="container">
    <div class="user-profile">
        <div class="user-avatar">
            <img src="" alt="User Avatar">
        </div>
        <p><strong>Name:</strong> <%= searchingUser.getName() %>
        </p>
        <p><strong>Surname:</strong> <%= searchingUser.getSurname() %>
        </p>
        <p><strong>Email:</strong> <%= searchingUser.getEmail() %>
        </p>

        <%
            if (friend == null) {
        %>
        <a href="/sendRequestFriend?id=<%=searchingUser.getId()%>">
            <button class="add-friend-btn">Add Friend</button>
        </a>
        <% } %>
    </div>
</div>
</body>
</html>