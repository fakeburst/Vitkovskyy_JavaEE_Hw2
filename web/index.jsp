<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 31-Jan-17
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Test</title>
    </head>
    <body>
    <%
        String role = String.valueOf(request.getAttribute("role"));
        if (role.equals("generic")) {
            out.print("Register and login first");
        } else {
            if (role.equals("registered")) {
                out.println("Registered user page");
            } else if (role.equals("admin")){
                out.println("Admin 1337 page");
            }
        }
    %>
    <a href="/MyServlet">Back to login page</a>
    </body>
</html>
