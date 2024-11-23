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
<html>
<head>
    <title>Search Users</title>
    <link rel="stylesheet" href="../css/search.css">
    <style>
        .remove-friend-btn {
            background-color: #0b3e00;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            margin-top: 10px;
        }

        .remove-friend-btn:hover {
            background-color: #003e05;
        }
    </style>
</head>
<body>
<%
    List<Friend> friends = (List<Friend>) request.getAttribute("friends");
%>
<section class="container">
    <div class="header">
        <h2>REQUESTS</h2>
    </div>

    <div class="button-container">
        <a href="/home" class="home-button">Go to Home Page</a>
    </div>

    <div class="user-list-container">
        <%
            for (Friend friend : friends) {
        %>
        <div class="user-card">
            <img src="https://randomuser.me/api/portraits/men/1.jpg" alt="User Profile Image" class="user-img">
            <div class="user-info">
                <h3 style="text-decoration: none;color: #2d0157"><%= friend.getSender().getName() + " " + friend.getSender().getSurname() + " " + friend.getDate() %>
                </h3>
            </div>
            <a href="/addFriend?id=<%=friend.getSender().getId()%>" class="remove-friend-btn">ADD FRIEND</a>
        </div>
        <% } %>
    </div>
</section>
</body>
</html>