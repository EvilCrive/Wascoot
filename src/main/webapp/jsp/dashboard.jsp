 <%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Dashboard</title>
	</head>

	<body>
		<h1>Dashboard</h1>
		<hr/>

		<!-- display the message -->
		<c:import url="/jsp/include/show-message.jsp"/>

		<!-- display the list of found scooterracks, if any -->
		<c:if test='${not empty scooterRackList}'>
			<table>
				<thead>
					<tr>
						<th>id</th><th>Total parking spots</th><th>Available parking spots</th><th>Postal code</th><th>Address</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="scooterrack" items="${scooterRackList}">
						<tr>

							<td><c:out value="${scooterrack.id}"/></td>
							<td><c:out value="${scooterrack.totalParkingSpots}"/></td>
							<td><c:out value="${scooterrack.availableParkingSpots}"/></td>
							<td><c:out value="${scooterrack.postalCode}"/></td>
							<td><c:out value="${scooterrack.address}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</body>
</html>
