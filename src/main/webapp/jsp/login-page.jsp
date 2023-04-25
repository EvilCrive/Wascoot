
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
       <meta charset="utf-8">
       <title>Administrator login</title>
    </head>
    <body>

        <form method="POST" action="<c:url value="/admin/login/"/>">
            <label for="email">Email:</label>
            <input  id="email" name="email" type="text"/><br/><br/>
            <label for="password">Password:</label>
            <input id="password" name="password" type="password"/><br/><br/>

            <button type="submit">Submit</button><br/>

        </form>
        <input type=button onClick="location.href='../jsp/dashboard.jsp'" value='Login'>


        <br/><br/>
        <input type=button onClick="location.href='../html/homepage.html'" value='home page'>
    </body>
</html>