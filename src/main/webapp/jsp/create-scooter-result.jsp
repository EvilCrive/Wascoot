
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Create Scooter</title>
</head>

<body>
<h1>Create Scooter</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the just created model, if any and no errors -->
<c:if test='${not empty newScooter && !message.error}'>
  <ul>
    <li>id: <c:out value="${newScooter.id}"/></li>
    <li>date of purchase: <c:out value="${newScooter.dateOfPurchase}"/></li>
    <li>km traveled: <c:out value="${newScooter.kmTraveled}"/></li>
    <li>model: <c:out value="${newScooter.model}"/></li>
    <li>remaining battery life: <c:out value="${newScooter.remainingBatteryLife}"/></li>
    <li>id scooterrack: <c:out value="${newScooter.idScooterrack}"/></li>
  </ul>
</c:if>

<a href="${pageContext.request.contextPath}/jsp/Menu.jsp">go back to the homepage</a>
</body>
</html>
