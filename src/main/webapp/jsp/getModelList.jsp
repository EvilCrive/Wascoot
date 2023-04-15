
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Get models Form</title>
	</head>

  <body>
	<h1>Get models Form</h1>

	<form method="GET" action="<c:url value="/model"/>">

		<button type="submit">Submit</button><br/>
		<button type="reset">Reset the form</button>
	</form>
	<h3>Get Customer Form</h3>
	<form method="get" action="<c:url value="/customer"/>">
		<button type="submit">Show Customers</button>
	</form>

	<h3>Get Payment Method Form</h3>
	<form method="get" action="<c:url value="/paymentMethod"/>">
		<button type="submit">Show Payment Methods</button>
	</form>
	</body>
</html>
