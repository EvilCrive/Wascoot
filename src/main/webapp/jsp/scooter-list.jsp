<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>get scooters</title>
</head>

<body>
<h1>get scooters</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the list of found model, if any -->
<c:if test='${not empty scootersList}'>
  <table>
    <thead>
    <tr>
      <th>id</th><th>date of purchase</th><th>km traveled</th><th>model</th><th>remaining battery life</th><th>id scooter rack</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="scooter" items="${scootersList}">
      <tr>

        <td><c:out value="${scooter.id}"/></td>
        <td><c:out value="${scooter.dateOfPurchase}"/></td>
        <td><c:out value="${scooter.kmTraveled}"/></td>
        <td><c:out value="${scooter.model}"/></td>
        <td><c:out value="${scooter.remainingBatteryLife}"/></td>
        <td><c:out value="${scooter.idScooterrack}"/></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>

<h1>Update Scooterrack</h1>
<form method="POST" action="<c:url value="/update-scooterrack"/>">
  <label for="ID">ID:</label>
  <input id="ID" name="id" type="text"/><br/>

  <label for="totalParkingSpotsID">total parking spots:</label>
  <input id="totalParkingSpotsID" name="total_parking_spots" type="text"/><br/>

  <label for="availableParkingSpotsID">available parking spots:</label>
  <input id="availableParkingSpotsID" name="available_parking_spots" type="text"/><br/>

  <label for="postalCodeID">postal code:</label>
  <input id="postalCodeID" name="postalcode" type="text"/><br/><br/>

  <label for="addressID">address:</label>
  <input id="addressID" name="address" type="text"/><br/><br/>

  <button type="submit">Update</button><br/>
  <button type="reset">Reset the form</button>
</form>

<button type="button" onclick="window.location.href='${pageContext.request.contextPath}/jsp/create-scooterrack-form.jsp';">Create</button>

<a href="${pageContext.request.contextPath}/jsp/Menu.jsp">go back to the homepage</a>
</body>
</html>