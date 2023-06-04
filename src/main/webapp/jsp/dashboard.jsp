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
	    <h1 class="title" >Dashboard</h1>
	    <div class="container">
            <div id="top-elements">

                <div id="map-container">
                    <h4 class="chart-text" >Scooterracks available information with geographic representation</h4>
                    <div id="map"></div>
                </div>

                <div id="topLocationTable">
                    <c:if test='${not empty topLocation}'>
                        <c:set var="count" value="0" scope="page" />
                    <table class="table-top">
                        <thead class="head-top">
                        <tr>
                            <th>Rank</th>
                            <th>id</th>
                        </tr>
                        </thead>

                        <tbody class="body-top">
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

                <div class="chart-container" id="age-chart-container">
                    <h4 class="chart-text">Age distribution by postal code</h4>
                    <canvas id="age-chart"></canvas>
                </div>
            </div>

            <div id="bottom-elements">
                <div class="chart-container" id="revenue-chart-container">
                    <canvas id="revenue-chart"></canvas>
                    <h4 class="chart-text">Revenue chart </h4>
                </div>

                <div class="chart-container" id="gender-chart-container">
                    <canvas id="gender-chart"></canvas>
                    <h4 class="chart-text">Gender distribution among customers</h4>
                </div>
            </div>

        </div>
	</body>
</html>
