 <%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Dashboard</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/utils.js"></script>
        <script src="${pageContext.request.contextPath}/js/dashboard.js"></script>

        <!-- Charts -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">

        <!--- Map representation with Leaflet -->
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY=" crossorigin="" />
        <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js" integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo=" crossorigin=""></script>

	</head>

	<body>
		<h1>Dashboard</h1>

		<hr/>

		<!-- display the message -->
		<c:import url="/jsp/include/show-message.jsp"/>

        <div id="map"></div>

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

        <div id="age-chart-container">
            <canvas id="age-chart"></canvas>
        </div>

        <div id="gender-chart-container">
            <canvas id="gender-chart"></canvas>
        </div>
	</body>
</html>
