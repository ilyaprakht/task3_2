<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <title>MeteoFinder</title>
    <body>
        <h2>Wellcome to find meteo!</h2>
        <br>
        <form method="get" action="jsp/find_meteo.jsp">
            <p>
                <b>Enter the city:</b>
                <br><br>
                <input type="text" name="city" size="100">
                <br><br>
                <input type="submit" value="Start search">
            </p>
        </form>
    </body>
</html>
