<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>get Rentals</title>
</head>

<body>
<h1>get rentals</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the list of found rental, if any -->
<c:if test='${not empty rentalsList}'>
    <table>
        <thead>
        <tr>
            <th>id</th><th>date hour delivery</th><th>date hour collection</th><th>id scooter</th>
            <th>scooterrack delivery</th><th>scooterrack collection</th><th>customer</th><th>km traveled</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="rental" items="${rentalsList}">
            <tr>

                <td><c:out value="${rental.id}"/></td>
                <td><c:out value="${rental.dateHourDelivery}"/></td>
                <td><c:out value="${rental.dateHourCollection}"/></td>
                <td><c:out value="${rental.idScooter}"/></td>
                <td><c:out value="${rental.scooterrackDelivery}"/></td>
                <td><c:out value="${rental.scooterrackCollection}"/></td>
                <td><c:out value="${rental.customer}"/></td>
                <td><c:out value="${rental.kmTraveled}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<a href="${pageContext.request.contextPath}/jsp/Menu.jsp">go back to the homepage</a>
</body>
</html>