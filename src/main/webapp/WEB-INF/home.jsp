<%@ page import="org.example.myfriend.model.User" %><%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 22.11.24
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="../css/home.css">
</head>
<body>
<% User user = (User) session.getAttribute("user"); %>
<header>
    <div class="header-content">
        <h1>Welcome, <%= user.getName() + " " + user.getSurname() %>!</h1>
        <img class="avatar" src="/getImage?imgName=<%=user.getImagePath()%>" alt="User Avatar">
    </div>
</header>

<nav>
    <ul class="menu">
        <li><a href="/editUser">Edit Profile</a></li>
        <li><a href="/search">Search Users</a></li>
        <li><a href="/request">Requests</a></li>
        <li><a href="/friend">Friends</a></li>
        <li><a href="/sendMessage">MESSAGE</a></li>
        <li><a href="/logout">Logout</a></li>
    </ul>
</nav>

<main>
    <p>Manage your account and explore features using the menu above.</p>
</main>

<footer>
    <p>&copy; 2024 MY FRIEND</p>
</footer>
</body>
</html>