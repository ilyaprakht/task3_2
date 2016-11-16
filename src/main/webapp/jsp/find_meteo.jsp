<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <title>Title</title>
    <body>
        <%@ page import="java.sql.*" %>
        <%
            String city = request.getParameter("city");
        %>
        <h3><%= ((city != null) && (!city.isEmpty()) ? city : "Enter city title!") %></h3>

        <br><br>
        <input type="submit" value="Back" onclick="history.back()">
    </body>
</html>
