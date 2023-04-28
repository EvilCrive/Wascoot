
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>WASCOOT Homepage</title>
</head>
<body>

<input type=button onClick="location.href='../jsp/dashboard.jsp'" value='Enter dashboard'>
<br/><br/>
<form method="GET" action="<c:url value="/model/list/"/>">

    <button type="submit">Enter Model</button><br/>
</form>
<br/>
<form method="GET" action="<c:url value="/list-customer"/>">

    <button type="submit">Enter Customer</button><br/>
</form>
<br/>
<form method="GET" action="<c:url value="/paymentmethod/list/"/>">

    <button type="submit">Enter Payment Method</button><br/>
</form>
<br/>
<form method="GET" action="<c:url value="/subscription/list/"/>">

    <button type="submit">Enter Subscription</button><br/>
</form>
<br/>
<input type=button onClick="location.href='../jsp/manage-pages/rental-list.jsp'" value='Enter rental list'>
<br/><br/>
<form method="GET" action="<c:url value="/scooterrack/list/"/>">

    <button type="submit">Enter Scooter Rack</button><br/>
</form>
<br/>
<form method="GET" action="<c:url value="/scooter/list/"/>">

    <button type="submit">Enter Scooter</button><br/>
</form>


<br/><br/><br/><br/>
<h1>
    Administrator
</h1>
<form method="POST" action="../html/create-administrator-form.html">
    <label>create administrator:</label><br/>
    <button type="submit">without photo</button>
</form>

<form method="POST" action="../jsp/create-administrator-form.jsp">
    <button type="submit">with photo</button><br/><br/><br/>
</form>

<form method="POST" action="../jsp/search-administrator-Id-form.jsp">
    <label>Search Admin:</label><br/>
    <button type="submit">Enter ID </button>
</form>

<form method="POST" action="../jsp/search-administrator-form.jsp">
    <button type="submit">Enter Email</button>
</form>


</body>
</html>