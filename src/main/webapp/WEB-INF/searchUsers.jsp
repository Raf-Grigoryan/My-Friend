<%@ page import="org.example.myfriend.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 22.11.24
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Users</title>
    <link rel="stylesheet" href="../css/search.css">
</head>
<body>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<section class="container">
    <div class="header">
        <h2>Users</h2>
        <form class="search-box" action="/search" method="get">
            <input type="text" name="username" placeholder="Search"/>
            <button type="submit">Search</button>
        </form>
    </div>

    <div class="button-container">
        <a href="/home" class="home-button">Go to Home Page</a>
    </div>

    <div class="user-list-container">
        <%
            for (User user : users) {%>
        <div class="user-card">
            <img src="https://randomuser.me/api/portraits/men/1.jpg" alt="User Profile Image" class="user-img">
            <div class="user-info">
                <a href="/singlePage?id=<%=user.getId()%>"><h3 style="text-decoration: none;color: #2d0157"><%= user.getName() + " " + user.getSurname() %>
                </h3></a>
                </p>
            </div>
        </div>
        <% } %>
    </div>
</section>
</body>
</html>