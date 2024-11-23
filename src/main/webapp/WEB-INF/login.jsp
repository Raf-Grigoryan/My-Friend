<%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 22.11.24
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../css/register.css">
</head>
<body>
<div class="login">
    <%
        String msg = (String) session.getAttribute("loginMessage");
        if (msg != null) {%>
    <h3 style="color: red"><%=msg%>
    </h3>
    <%
        session.removeAttribute("loginMessage");
    } else {
    %>
    <h1>Login</h1>
    <% }%>

    <h3><a href="/register" style="text-decoration: none;color: white">-->Register</a></h3>
    <form method="post" action="/login">
        <input type="text" name="email" placeholder="Username" required="required"/>
        <input type="password" name="password" placeholder="Password" required="required"/>
        <button type="submit" class="btn btn-primary btn-block btn-large">Login</button>
    </form>
</div>
</body>
</html>
