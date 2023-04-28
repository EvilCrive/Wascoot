
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
       <meta charset="utf-8">
       <title>Welcome to WASCOOT</title>
    </head>
    <body>
    <h1>
        Wascoot Login Page
    </h1>
    <h3>Please Enter your Email and Password to login</h3>
        <form method="POST" action="<c:url value="/admin/login/"/>">
            <label for="email">Email:</label>
            <input  id="email" name="email" type="text"
                    required placeholder="Insert here your email"/><br/><br/>
            <label for="password">Password:</label>
            <input id="password" name="password" type="password"
                   placeholder="Insert here your password"/><br/><br/>

            <button type="submit">Login</button><br/>

        </form>


    </body>
</html>