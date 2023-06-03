 <%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Dashboard</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/utils.js"></script>
        <script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">

        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY=" crossorigin="" />
        <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js" integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo=" crossorigin=""></script>

	</head>

	<body>
	    <div class="parent">
		<h1>Dashboard</h1>

		<hr/>

		<!-- display the message -->
		<c:import url="/jsp/include/show-message.jsp"/>

        <div class="div1">

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

         <div id="map"></div>

        </div>
        <div class="div2">

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

		</div>
        <div class="div3">

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

        </div>
        <div class="div4">

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

        </div>
        <div class="div5">

        <div id="age-chart-container">
            <canvas id="age-chart"></canvas>
        </div>

        </div>
        <div class="div6">

        <div id="gender-chart-container">
            <canvas id="gender-chart"></canvas>
        </div>

        <div id="map"></div>

	</body>
</html>
