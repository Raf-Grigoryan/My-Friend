<%@ page import="org.example.myfriend.model.User" %><%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 23.11.24
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<%User user = (User) session.getAttribute("user");%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="../css/userProfile.css">
</head>
<body>

<header>
    <nav>
        <ul>
            <li><a href="/home">HOME</a></li>
            <li><a href="/editUser">Edit Profile</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>
</header>
<div class="profile-container">
    <div class="profile-avatar">
        <img src="/getImage?imagName=<%=user.getImagePath()%>" alt="User Avatar">
    </div>
    <div class="profile-info">
        <h1><%= user.getName() %> <%= user.getSurname() %>
        </h1>
        <p>Email: <%= user.getEmail() %>
        </p>
    </div>
</div>
</body>
</html>