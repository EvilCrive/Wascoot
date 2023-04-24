
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Get List of Entities</title>
</head>

<body>
<h1>Get models List</h1>

<form method="GET" action="<c:url value="/model"/>">

    <button type="submit">Show</button><br/>
</form>


<h1> Get Payment method list</h1>
<form method="GET" action="<c:url value="/paymentmethod"/>">

    <button type="submit">Show</button><br/>
</form>

<h1> Get Customer list</h1>
<form method="GET" action="<c:url value="/list-customer"/>">

    <button type="submit">Show</button><br/>
</form>

<h1> Get Subscription list</h1>
<form method="GET" action="<c:url value="/list-subscription"/>">

    <button type="submit">Show</button><br/>
</form>
</body>
</html>