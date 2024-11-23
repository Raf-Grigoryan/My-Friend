<%@ page import="org.example.myfriend.model.User" %><%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 23.11.24
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Profile</title>
  <link rel="stylesheet" href="../css/editUser.css">
</head>
<body>
<header>
  <h1>Edit Your Profile</h1>
</header>
<%User user = (User) session.getAttribute("user");%>
<div class="container">
  <form action="/editUser" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" id="name" name="name" value="<%=user.getName()%>" required>
    </div>
    <div class="form-group">
      <label for="surname">Surname:</label>
      <input type="text" id="surname" name="surname" value="<%=user.getSurname()%>" required>
    </div>
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="text" id="email" name="email" value="<%=user.getEmail()%>" disabled>
    </div>
    <div class="form-group">
      <label for="password">New Password:</label>
      <input type="text" id="password" name="password" value="<%=user.getPassword()%>">
    </div>
    <div class="form-group">
      <label for="image">Upload New Image:</label>
      <input type="file" id="image" name="image">
    </div>
    <div class="action-buttons">
      <button type="submit" class="btn btn-save">Save Changes</button>
      <button type="button" class="btn btn-cancel" onclick="window.location.href='/home';">Cancel</button>
    </div>
  </form>
</div>
</body>
</html>