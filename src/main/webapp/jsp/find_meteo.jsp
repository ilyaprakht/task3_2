<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <title>Title</title>
    <body>
        <%@ page import="com.nc.task3.controller.Controller" %>
        <%@ page import="com.nc.task3.controller.SimpleResult" %>
        <%@ page import="com.nc.task3.utils.SpringUtils" %>
        <%@ page import="com.nc.task3.controller.ControllerService" %>
        <%
            String city = request.getParameter("city");

            String message = "";
            if ((city == null) || city.isEmpty()) {
                message = "Enter city title!";
            } else {
                Controller controller = ((ControllerService) SpringUtils.getBean("controllerService")).getFindMeteoController();
                SimpleResult result = (SimpleResult) controller.handle(city);
                if (result.getResult()) {
                    message = "Weather successfully saved";
                } else {
                    message = "Weather didn`t saved. There was some errors";
                }
                message += (result.getMessage() == null ? "" : ". " + result.getMessage());
            }
        %>
        <h3><%= message %></h3>

        <br><br>
        <input type="submit" value="Back" onclick="history.back()">
    </body>
</html>
