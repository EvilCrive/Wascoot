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


		<!-- display the list of found PaymentWithoutSubscriptions, if any -->
		<c:if test='${not empty paymentWithoutSubscriptionList}'>
			<table>
				<thead>
					<tr>
						<th>id</th><th>price</th><th>date hour</th><th>order id</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="paymentwithoutsubscription" items="${paymentWithoutSubscriptionList}">
						<tr>

							<td><c:out value="${paymentwithoutsubscription.id}"/></td>
							<td><c:out value="${paymentwithoutsubscription.price}"/></td>
							<td><c:out value="${paymentwithoutsubscription.dateHour}"/></td>
							<td><c:out value="${paymentwithoutsubscription.orderId}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

		<c:if test='${not empty topLocation}'>
			<c:set var="count" value="0" scope="page" />
			<table>
				<thead>
				<tr>
					<th>Rank</th>
					<th>id</th>
				</tr>
				</thead>

				<tbody>
				<c:forEach var="topLocation" items="${topLocation}">
					<c:set var="count" value="${count + 1}" scope="page"/>
					<tr>
						<td><c:out value="${count}"/></td>
						<td><c:out value="${topLocation}"/></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</c:if>

		<c:if test='${not empty revenueList}'>
			<c:set var="sum" value="0" scope="page" />
			<table>
				<thead>
				<tr>
					<th>Month</th>
					<th>Revenue</th>
				</tr>
				</thead>

				<tbody>
				<c:forEach var="revenue" items="${revenueList}">
					<c:set var="sum" value="${sum + revenue.price}" scope="page"/>
					<tr>
						<td><c:out value="${revenue.date}"/></td>
						<td><c:out value="${revenue.price}"/> $</td>
					</tr>
				</c:forEach>
				<tr>
					<td>Total: </td>
					<td><c:out value="${sum}"/> $ </td>
				</tr>
				</tbody>
			</table>
		</c:if>
	</body>
</html>
