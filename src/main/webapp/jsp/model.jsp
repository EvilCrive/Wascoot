
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>get models</title>
</head>

<body>
<h1>get models</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the list of found model, if any -->
<c:if test='${not empty modelList}'>
	<table>
		<thead>
		<tr>
			<th>name</th><th>brand</th><th>battery life</th><th>price p/min</th>
		</tr>
		</thead>

		<tbody>
		<c:forEach var="model" items="${modelList}">
			<tr>

				<td><c:out value="${model.name}"/></td>
				<td><c:out value="${model.brand}"/></td>
				<td><c:out value="${model.batteryLife}"/></td>
				<td><c:out value="${model.pricePerMin}"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</c:if>
</body>
</html>
