<%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 22.11.24
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="../css/register.css">
</head>
<body>
<div class="login">
    <%
        String registerMessage = (String) session.getAttribute("registerMessage");
        if (registerMessage != null) {%>
    <h3 style="color: darkred"><%=registerMessage%>
    </h3>
    <%
        session.getAttribute("registerMessage");
    } else {
    %>
    <h1>REGISTER</h1>
    <%}%>
    <form method="post" action="/register">
        <h3><a href="/login" style="text-decoration: none;color: white">-->Login</a></h3>
        <input type="text" name="name" placeholder="Name" required="required"/>
        <input type="text" name="surname" placeholder="Surname" required="required"/>
        <input type="text" name="email" placeholder="Email" required="required"/>
        <input type="password" name="password" placeholder="Password" required="required"/>
        <button type="submit" class="btn btn-primary btn-block btn-large">Register</button>
    </form>
</div>
</body>
</html>
